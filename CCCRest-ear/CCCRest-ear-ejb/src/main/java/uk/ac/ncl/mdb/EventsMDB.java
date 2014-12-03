package uk.ac.ncl.mdb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.hornetq.jms.client.HornetQBytesMessage;

import uk.ac.ncl.erop.ContractComplianceChecker;
import uk.ac.ncl.erop.Event;
import uk.ac.ncl.logging.CCCLogger;
import uk.ac.ncl.model.BusinessEvent;
import uk.ac.ncl.conf.ConfigurationFilesEnum;
import uk.ac.ncl.util.Resources;
import uk.ac.ncl.xml.CCCResponse;
import uk.ac.ncl.util.Resources;


/**
 * The Class EventsMDB.
 *  Message Driven Bean for Business Events
 */
@MessageDriven(name = "EventsMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/events"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class EventsMDB implements MessageListener {

	private final static Logger log = Logger.getLogger(EventsMDB.class.toString());
	private String payloadXML;

	private BusinessEvent bEvent;
	
	@PersistenceContext(unitName = "RopePU")
	private EntityManager em;

	private static ContractComplianceChecker ccc;
	private List<Event> events = new ArrayList<Event>();

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message rcvMessage) {

		Resources.setEntityManager(em);

		ObjectMessage msg = null;
		try {
			if (rcvMessage instanceof ObjectMessage) {
				msg = (ObjectMessage) rcvMessage;
				Object o = msg.getObject();
				log.info("Received object: " + o);
				BusinessEvent res = (BusinessEvent) msg.getObject();
				log.info("Received my resource: " + res);
			} else if (rcvMessage instanceof BytesMessage) {

				bEvent = receiveBusinessEventMsg(rcvMessage);

				ccc = ContractComplianceChecker.createContractComplianceChecker(ConfigurationFilesEnum.CHANGESET_XML.getConfigurationFilePath());
				
				Event event;
				List<CCCResponse> responses;
				CCCResponse cccResponse = new CCCResponse();
				cccResponse.setContractCompliant(false);

				log.info("CCC started? " + ccc.cccStarted());
				if(!ccc.cccStarted()) {
					ccc.initializeCCC();
				}

				event = getEvent(bEvent);
				log.info("event: " + event);
				CCCLogger.logTrace("---------------------- START ---------------------");
                CCCLogger.logTrace("Business event: " + event );
				cccResponse = processCCCEvent(event);

				log.info("cccResponse: " + cccResponse);
                CCCLogger.logTrace("cccResponse: " + cccResponse);
				CCCLogger.logTrace("---------------------- END -----------------------");
				sendResponse(cccResponse);

			} else {
				log.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		} catch (JAXBException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Process ccc event.
	 *
	 * @param event the event
	 * @return the cCC response
	 */
	private CCCResponse processCCCEvent(Event event) {
		CCCResponse cccResponse;
		if (!ccc.cccStarted()) {
			ccc.initializeCCC();
			cccResponse = ccc.processEvent(event);
		} else {
			cccResponse = ccc.processEvent(event);
		}
		return cccResponse;
	}

	/**
	 * Receive business event msg.
	 *
	 * @param rcvMessage the rcv message
	 * @return the business event
	 * @throws JAXBException the jAXB exception
	 * @throws JMSException the jMS exception
	 */
	private BusinessEvent receiveBusinessEventMsg(Message rcvMessage) throws JAXBException, JMSException {
		JAXBContext jaxbContext = JAXBContext.newInstance(BusinessEvent.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(getStringFromBytesMessage(rcvMessage));
		BusinessEvent bEvent = (BusinessEvent) unmarshaller.unmarshal(reader);
		log.info("Received BusinessEvent: " + bEvent.toString());
		return bEvent;
	}

	/**
	 * Send response.
	 *
	 * @param cccResponse the ccc response
	 * @throws NamingException the naming exception
	 * @throws JMSException the jMS exception
	 * @throws JAXBException the jAXB exception
	 * @throws PropertyException the property exception
	 */
	private void sendResponse(CCCResponse cccResponse) throws NamingException, JMSException, JAXBException,
			PropertyException {
		InitialContext ic = new InitialContext();
		ConnectionFactory connectionFactory = (ConnectionFactory) ic.lookup("/ConnectionFactory");
		Queue replyQueue = (Queue) ic.lookup("/queue/replyQueue");

		Connection conn = connectionFactory.createConnection();

		Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

		MessageProducer producer = sess.createProducer(replyQueue);


		JAXBContext jaxbContext1 = JAXBContext.newInstance(CCCResponse.class);
		Marshaller jaxbMarshaller = jaxbContext1.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		jaxbMarshaller.marshal(cccResponse, System.out);

		ObjectMessage message = sess.createObjectMessage();
		message.setObject(cccResponse);
		message.setStringProperty(org.hornetq.rest.HttpHeaderProperty.CONTENT_TYPE, "application/xml");
		producer.send(message);

		if (conn != null) {
			try {
				conn.close();
			} catch (JMSException e) {
			}
		}
	}

	/**
	 * Gets the event from the Business Event message.
	 *
	 * @param bEvent the b event
	 * @return the event
	 */
	private Event getEvent(BusinessEvent bEvent) {
		Event event;
		if (bEvent.getType().equals("reset")) {
			event = new Event(bEvent.getType());
		} else {
			event = new Event(bEvent.getSequenceId(),bEvent.getOriginator(), bEvent.getResponder(), bEvent.getType(),
					bEvent.getStatus());
		}
		return event;
	}

	/**
	 * Gets the string from bytes message.
	 *
	 * @param rcvMessage
	 *            the rcv message
	 * @return
	 * @return the string from bytes message
	 * @throws JMSException
	 *             the jMS exception
	 */
	private String getStringFromBytesMessage(Message rcvMessage) throws JMSException {
		HornetQBytesMessage bmsg = (HornetQBytesMessage) rcvMessage;
		byte[] bytes;
		bytes = new byte[(int) bmsg.getBodyLength()];
		bmsg.readBytes(bytes);
		payloadXML = new String(bytes);
		log.info("Received message: " + payloadXML);
		return payloadXML;
	}

	/**
	 * To byte array.
	 *
	 * @param obj
	 *            the obj
	 * @return the byte[]
	 */
	public byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			bos.close();
			bytes = bos.toByteArray();
		} catch (IOException ex) {
		}
		return bytes;
	}

}
