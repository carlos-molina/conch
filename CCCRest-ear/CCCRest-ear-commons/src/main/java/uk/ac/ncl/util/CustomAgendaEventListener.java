/**
 * 
 */
package uk.ac.ncl.util;

import java.util.logging.Logger;

import org.drools.WorkingMemory;
import org.drools.event.AgendaEventListener;
import org.drools.event.RuleFlowGroupActivatedEvent;
import org.drools.event.RuleFlowGroupDeactivatedEvent;
import org.drools.event.rule.ActivationCancelledEvent;
import org.drools.event.rule.ActivationCreatedEvent;
import org.drools.event.rule.AfterActivationFiredEvent;
import org.drools.event.rule.AgendaGroupPoppedEvent;
import org.drools.event.rule.AgendaGroupPushedEvent;
import org.drools.event.rule.BeforeActivationFiredEvent;
import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.event.rule.ObjectRetractedEvent;
import org.drools.event.rule.ObjectUpdatedEvent;
import org.drools.event.rule.WorkingMemoryEventListener;

import uk.ac.ncl.util.CustomWorkingMemoryEventListener;

public class CustomAgendaEventListener implements AgendaEventListener, WorkingMemoryEventListener {
	private final static Logger logger = Logger.getLogger(CustomAgendaEventListener.class.toString());

	public void activationCancelled(ActivationCancelledEvent event) {
		logger.info("Activation Cancelled: " + event.getActivation());
	}

	public void activationCreated(ActivationCreatedEvent event) {
		logger.info("Activation Created: " + event.getActivation());
	}

	public void beforeActivationFired(BeforeActivationFiredEvent event) {
		logger.info("Before Activation Fired: " + event.getActivation());
	}

	public void afterActivationFired(AfterActivationFiredEvent event) {
		logger.info("After Activation Fired: " + event.getActivation());
	}

	public void agendaGroupPopped(AgendaGroupPoppedEvent event) {

		logger.info("Agenda Group Popped: " + event.getAgendaGroup());
	}

	public void agendaGroupPushed(AgendaGroupPushedEvent event) {
		logger.info("Agenda Group Pushed: " + event.getAgendaGroup());
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#activationCancelled(org.drools.event.ActivationCancelledEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void activationCancelled(org.drools.event.ActivationCancelledEvent event, WorkingMemory wm) {
		logger.info("Activation Cancelled: " + event.getActivation() + " " + "Working Memory: " + wm.toString());
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#activationCreated(org.drools.event.ActivationCreatedEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void activationCreated(org.drools.event.ActivationCreatedEvent event, WorkingMemory wm) {
		logger.info("Activation Created: " + event.getActivation() + " " + "Working Memory: " + wm.toString());
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#afterActivationFired(org.drools.event.AfterActivationFiredEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void afterActivationFired(org.drools.event.AfterActivationFiredEvent event, WorkingMemory wm) {
		logger.info("After Activation Fired: " + event.getActivation() + " " + "Working Memory: " + wm.toString());
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#afterRuleFlowGroupActivated(org.drools.event.RuleFlowGroupActivatedEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#afterRuleFlowGroupDeactivated(org.drools.event.RuleFlowGroupDeactivatedEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#agendaGroupPopped(org.drools.event.AgendaGroupPoppedEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void agendaGroupPopped(org.drools.event.AgendaGroupPoppedEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#agendaGroupPushed(org.drools.event.AgendaGroupPushedEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void agendaGroupPushed(org.drools.event.AgendaGroupPushedEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#beforeActivationFired(org.drools.event.BeforeActivationFiredEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void beforeActivationFired(org.drools.event.BeforeActivationFiredEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#beforeRuleFlowGroupActivated(org.drools.event.RuleFlowGroupActivatedEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.AgendaEventListener#beforeRuleFlowGroupDeactivated(org.drools.event.RuleFlowGroupDeactivatedEvent, org.drools.WorkingMemory)
	 */
	@Override
	public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0, WorkingMemory arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.rule.WorkingMemoryEventListener#objectInserted(org.drools.event.rule.ObjectInsertedEvent)
	 */
	@Override
	public void objectInserted(ObjectInsertedEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.rule.WorkingMemoryEventListener#objectUpdated(org.drools.event.rule.ObjectUpdatedEvent)
	 */
	@Override
	public void objectUpdated(ObjectUpdatedEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.drools.event.rule.WorkingMemoryEventListener#objectRetracted(org.drools.event.rule.ObjectRetractedEvent)
	 */
	@Override
	public void objectRetracted(ObjectRetractedEvent event) {
		// TODO Auto-generated method stub
		
	}
}
