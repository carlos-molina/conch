package uk.ac.ncl.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import uk.ac.ncl.model.BusinessEvent;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;


@RequestScoped
public class BusinessEventListProducer {
   @Inject
   private EntityManager em;

   private List<BusinessEvent> events;

   // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
   // Facelets or JSP view)
   @Produces
   @Named
   public List<BusinessEvent> getBusinessEvents() {
      return events;
   }

   public void onBusinessEventListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final BusinessEvent bEvent) {
      retrieveAllBusinessEventsByType();
   }

   @PostConstruct
   public void retrieveAllBusinessEventsByType() {

      //using Hibernate Session and Criteria Query via Hibernate Native API
      Session session = (Session) em.getDelegate();
      Criteria cb = session.createCriteria(BusinessEvent.class);
      cb.addOrder(Order.asc("type"));
      events = (List<BusinessEvent>)cb.list();

   }
}
