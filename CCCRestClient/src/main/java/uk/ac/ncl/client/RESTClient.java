package uk.ac.ncl.client;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.spi.Link;

/**
 * The Class RESTClient.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: b1049501 $$
 * @version $$Revision: 398 $$, $$Date: 2012-08-30 13:03:21 +0100 (Thu, 30 Aug
 *          2012) $$
 */
public class RESTClient {

//	private static final String BUSINESS_EVENT_QUEUE_URL = "http://ccc-110495017.rhcloud.com/CCCRest-ear-web/queues/jms.queue.events";
	private static final String BUSINESS_EVENT_QUEUE_URL = "http://localhost:8080/CCCRest-ear-web/queues/jms.queue.events";
//	private static final String REPLY_QUEUE_URL = "http://ccc-110495017.rhcloud.com/CCCRest-ear-web/queues/jms.queue.replyQueue";
	private static final String REPLY_QUEUE_URL = "http://localhost:8080/CCCRest-ear-web/queues/jms.queue.replyQueue";

	private final static Logger log = Logger.getLogger(RESTClient.class.toString());
	private static ClientRequest request;
	private static Map<String, File> files;
	private static Map<String, File> folders;
	private static String consumeNext;
	private static ClientResponse<?> res;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String folderPath = args[0];

		if (folderPath.equals("")) {

			System.out.println("blank folder path");
			System.exit(1);
		} else {
			files = new HashMap<String, File>();
			folders = new HashMap<String, File>();
			final File folder = new File(folderPath);

			res = createResourceForConsumer();

			listFolder(folder);
		}

	}

	/**
	 * List files for folder.
	 *
	 * @param folder
	 *            the folder
	 * @throws Exception
	 *             the exception
	 */
	public static void listFolder(final File folder) throws Exception {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				if (!folders.containsKey(fileEntry.getName())) {
					folders.put(fileEntry.getName(), fileEntry);
					// listFolder(fileEntry);
				}
			}
		}

		//System.out.println("folders: " + folders.toString());

		Object[] folderKeys = sortMap(folders);

		// System.out.println("folders keys sorted: " + folderKeys.toString());
		for (int i = 0; i < folderKeys.length; i++) {
			//System.out.println("folders keys: " + folderKeys[i]);
			listFiles(folders.get(folderKeys[i]));
		}

	}

	/**
	 * List files.
	 *
	 * @param folder
	 *            the folder
	 * @throws Exception
	 *             the exception
	 */
	public static void listFiles(final File folder) throws Exception {

		files.clear();
		// System.out.println("list files of folder: " + folder.getName());
		for (final File fileEntry : folder.listFiles()) {

			if (fileEntry.getName().endsWith(".xml")) {
				files.put(fileEntry.getName(), fileEntry);
			}

		}

		Object[] key = sortMap(files);

		for (int i = 0; i < key.length; i++) {
			System.out.println("\n");
			System.out.println("folder: " + files.get(key[i]).getParent() + " " + "\n");
			System.out.println("filename: " + files.get(key[i]).getName() + "\n");
			sendBusinessEvent(files.get(key[i]));
			receiveCCCResponse();
		}
		// System.out.println("\n Send Reset Event: ");
		// sendResetBusinessEvent();
		// receiveCCCResponse();

	}

	/**
	 * Send reset business event.
	 *
	 * @throws Exception
	 *             the exception
	 */
	private static void sendResetBusinessEvent() throws Exception {
		BusinessEvent resetEvent = new BusinessEvent("reset");

		request = new ClientRequest(BUSINESS_EVENT_QUEUE_URL);
		ClientResponse response = request.head();
		Link create = response.getHeaderAsLink("msg-create");
		System.out.println("-------- Begin Request to CCC service ----------\n");

		System.out.println(resetEvent);
		System.out.println("\n");

		System.out.println("-------- End Request to CCC service ----------\n");

		response = create.request().body("application/xml", resetEvent).post();
		// System.out.println("Status:" + response.getStatus());
		if (response.getStatus() != 201)
			throw new RuntimeException("Failed to post");
		request.clear();
		response.releaseConnection();

	}

	/**
	 * Gets the reset event.
	 *
	 * @return the reset event
	 */
	public static BusinessEvent getResetEvent() {
		return new BusinessEvent();
	}

	/**
	 * Sort map.
	 *
	 * @param files2
	 *            the files2
	 * @return the object[]
	 */
	private static Object[] sortMap(Map<String, File> files2) {
		Object[] keys = files2.keySet().toArray();
		Arrays.sort(keys);
		// for (Object key: keys) {
		// System.out.println("sorted keys: " + key);
		// }

		return keys;
	}

	/**
	 * Send business event.
	 *
	 * @param file
	 *            the file
	 * @throws Exception
	 *             the exception
	 */
	public static void sendBusinessEvent(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(BusinessEvent.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		BusinessEvent bevent = (BusinessEvent) jaxbUnmarshaller.unmarshal(file);
		System.out.println(bevent);

		request = new ClientRequest(BUSINESS_EVENT_QUEUE_URL);
		ClientResponse response = request.head();
		Link create = response.getHeaderAsLink("msg-create");
		System.out.println("-------- Begin Request to CCC service ----------\n");

		System.out.println(bevent);
		System.out.println("\n");

		System.out.println("-------- End Request to CCC service ----------\n");

		response = create.request().body("application/xml", bevent).post();
		// System.out.println("Status:" + response.getStatus());
		if (response.getStatus() != 201)
			throw new RuntimeException("Failed to post");
		request.clear();
		response.releaseConnection();

	}

	/**
	 * Receive ccc response.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public static void receiveCCCResponse() throws Exception {

		consumeNext = getNextMessageUrl(res);

		Link consume = new Link();
		consume.setHref(consumeNext);
		res = consume.request().header("Accept-Wait", "35").header("Accept", "application/xml").post();
		// System.out.println("response status: " + res.getStatus());
		// System.out.println("response: " + res.toString());

		if (res.getStatus() == 503) {
			System.out.println("\n");
			System.out.println("-------- Begin Response from CCC service ----------\n");
			System.out.println("No messages left in queue");
			System.out.println("\n");
			System.out.println("-------- End Response from CCC service ----------\n");
		} else if (res.getStatus() == 200) {
			consumeNext = getNextMessageUrl(res);

			CCCResponse response = (CCCResponse) res.getEntity(CCCResponse.class);
			// System.out.println(response);

			JAXBContext jaxbContext1 = JAXBContext.newInstance(CCCResponse.class);
			Marshaller jaxbMarshaller = jaxbContext1.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			System.out.println("\n");
			System.out.println("-------- Begin Response from CCC service ----------\n");
			jaxbMarshaller.marshal(response, System.out);
			System.out.println("\n");
			System.out.println("-------- End Response from CCC service ----------\n");

		} else {
			throw new RuntimeException("Failure: " + res.getStatus());
		}

	}

	/**
	 * Creates the resource for consumer.
	 *
	 * @return the client response
	 * @throws Exception
	 *             the exception
	 */
	private static ClientResponse createResourceForConsumer() throws Exception {
		ApacheHttpClient4Executor executor = new ApacheHttpClient4Executor(getThreadSafeClient());
		ClientRequest request = new ClientRequest(REPLY_QUEUE_URL, executor);
		ClientResponse res = request.head();
		Link pullConsumers = res.getHeaderAsLink("msg-pull-consumers");
		// System.out.println("pullConsumers: " + pullConsumers);
		res = pullConsumers.request().post();
		// Link ackNext = res.getHeaderAsLink("msg-acknowledge-next");
		consumeNext = getNextMessageUrl(res);
		return res;
	}

	/**
	 * Gets the next message url.
	 *
	 * @param res
	 *            the res
	 * @return the next message url
	 */
	private static String getNextMessageUrl(ClientResponse res) {
		MultivaluedMap<String, ?> headers = res.getHeaders();
		String consumeNext = (String) headers.getFirst("msg-consume-next");

		// System.out.println("consumeNext url: " + consumeNext);
		return consumeNext;
	}

	/**
	 * Gets the thread safe client.
	 *
	 * @return the thread safe client
	 */
	@SuppressWarnings("deprecation")
	public static DefaultHttpClient getThreadSafeClient() {

		DefaultHttpClient client = new DefaultHttpClient();
		ClientConnectionManager mgr = client.getConnectionManager();
		org.apache.http.params.HttpParams params = client.getParams();
		client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,

		mgr.getSchemeRegistry()), params);
		return client;
	}

	/**
	 * Gets the client.
	 *
	 * @return the client
	 */
	@SuppressWarnings("deprecation")
	public static void getClient() {
		BasicHttpParams params = new BasicHttpParams();
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, registry);
		DefaultHttpClient client = new DefaultHttpClient(cm, params);

	}

}
