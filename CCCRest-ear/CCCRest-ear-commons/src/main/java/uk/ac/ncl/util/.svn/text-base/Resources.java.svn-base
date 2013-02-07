package uk.ac.ncl.util;

import org.jboss.logging.Logger;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * The Class Resources.
 *
 * @author <a href="mailto:ioannis.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep 2012) $$
 */
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
