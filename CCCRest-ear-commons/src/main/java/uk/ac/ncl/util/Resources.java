package uk.ac.ncl.util;

import org.jboss.logging.Logger;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

   @PersistenceContext
   private static EntityManager em;

   public static void setEntityManager(EntityManager em) {
	   Resources.em = em;
   }

   public static EntityManager getEntityManager() {
	   return em;
   }
}
