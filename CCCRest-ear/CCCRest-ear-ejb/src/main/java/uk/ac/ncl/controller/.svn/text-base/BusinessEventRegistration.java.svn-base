package uk.ac.ncl.controller;

import java.io.Serializable;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import uk.ac.ncl.mdb.EventsMDB;
import uk.ac.ncl.model.BusinessEvent;

// The @Stateful annotation eliminates the need for manual transaction demarcation
//@Stateful
//@LocalBean


// The @Model stereotype is a convenience mechanism to make this a
// request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
//@Model
//@Stateful
public class BusinessEventRegistration implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
//	@EJB
//	private EventsMDB events;

	// @Inject
	private final static Logger log = Logger.getLogger(BusinessEventRegistration.class.toString());

	@PersistenceContext(unitName = "RopePU")
	private EntityManager em;
	// @Inject
	//@InjectedObject
	private Event<BusinessEvent> memberEventSrc;

	private BusinessEvent newBusinessEvent;

	// @Produces
	// @Named
	public BusinessEvent getNewBusinessEvent() {
		return newBusinessEvent;
	}

	//@Produces
	public void register(BusinessEvent newBusinessEvent) throws Exception {
		//events.
		log.info("adding new event " + newBusinessEvent.toString());
		//EntityManager em = entityManagerFactory.createEntityManager();
		//log.info("entity manager: " + em);
		em.persist(newBusinessEvent);

		try {
			memberEventSrc.fire(newBusinessEvent);
		} catch (Exception e) {
			log.info("Registration Failed!You have provided duplicate details for business " + "event!!!");
			e.printStackTrace();
		}

		// initNewMember();
	}

	// @PostConstruct
	public void initNewMember() {
		newBusinessEvent = new BusinessEvent();
	}

	public void addBusinessEvent(BusinessEvent bEvent) {

		this.newBusinessEvent = bEvent;
	}
}
