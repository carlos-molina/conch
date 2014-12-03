
package uk.ac.ncl.util;

import java.util.logging.Logger;

<<<<<<< HEAD:CCCRest-ear/CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java
import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.event.rule.ObjectRetractedEvent;
import org.drools.event.rule.ObjectUpdatedEvent;
import org.drools.event.rule.WorkingMemoryEventListener;
=======
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import uk.ac.ncl.logging.CCCLogger;
>>>>>>> added file logging configuration:CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java

public class CustomWorkingMemoryEventListener implements WorkingMemoryEventListener {
	
	private final static Logger logger = Logger.getLogger(CustomWorkingMemoryEventListener.class.toString());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.drools.event.rule.WorkingMemoryEventListener#objectInserted(org.drools
	 * .event.rule.ObjectInsertedEvent)
	 */
	@Override
	public void objectInserted(ObjectInsertedEvent event) {
<<<<<<< HEAD:CCCRest-ear/CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java
		logger.info("Object Inserted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKnowledgeRuntime());

=======
		logger.info("Object Inserted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
		CCCLogger.logTrace("Object Inserted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
>>>>>>> added file logging configuration:CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.drools.event.rule.WorkingMemoryEventListener#objectRetracted(org.
	 * drools.event.rule.ObjectRetractedEvent)
	 */
	@Override
<<<<<<< HEAD:CCCRest-ear/CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java
	public void objectRetracted(ObjectRetractedEvent event) {
		logger.info("Object Retracted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKnowledgeRuntime());
=======
	public void objectDeleted(ObjectDeletedEvent event) {
		logger.info("Object Retracted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
		CCCLogger.logTrace("Object Retracted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
>>>>>>> added file logging configuration:CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.drools.event.rule.WorkingMemoryEventListener#objectUpdated(org.drools
	 * .event.rule.ObjectUpdatedEvent)
	 */
	@Override
	public void objectUpdated(ObjectUpdatedEvent event) {
<<<<<<< HEAD:CCCRest-ear/CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java
		logger.info("Object Updated: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKnowledgeRuntime());
=======
		logger.info("Object Updated: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
		CCCLogger.logTrace("Object Updated: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
>>>>>>> added file logging configuration:CCCRest-ear-commons/src/main/java/uk/ac/ncl/util/CustomWorkingMemoryEventListener.java

	}
}
