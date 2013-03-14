
package uk.ac.ncl.util;

import java.util.logging.Logger;

import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.event.rule.ObjectRetractedEvent;
import org.drools.event.rule.ObjectUpdatedEvent;
import org.drools.event.rule.WorkingMemoryEventListener;

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
		logger.info("Object Inserted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKnowledgeRuntime());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.drools.event.rule.WorkingMemoryEventListener#objectRetracted(org.
	 * drools.event.rule.ObjectRetractedEvent)
	 */
	@Override
	public void objectRetracted(ObjectRetractedEvent event) {
		logger.info("Object Retracted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKnowledgeRuntime());

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
		logger.info("Object Updated: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKnowledgeRuntime());

	}
}
