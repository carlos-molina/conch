package uk.ac.ncl.util;

import org.kie.api.event.process.*;

import java.util.logging.Logger;

/**
 * Created by alpac on 03/11/2014.
 */
public class CustomProcessEventListener implements ProcessEventListener {
    private final static Logger logger = Logger.getLogger(CustomWorkingMemoryEventListener.class.toString());

    @Override
    public void beforeProcessStarted(ProcessStartedEvent event) {
        logger.info("beforeProcessStarted" + event.toString());
    }

    @Override
    public void afterProcessStarted(ProcessStartedEvent event) {
        logger.info("afterProcessStarted" + event.toString());
    }

    @Override
    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        logger.info("beforeProcessCompleted" + event.toString());
    }

    @Override
    public void afterProcessCompleted(ProcessCompletedEvent event) {
        logger.info("afterProcessStarted" + event.toString());
    }

    @Override
    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        logger.info("beforeNodeTriggered" + event.toString());
    }

    @Override
    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        logger.info("afterNodeTriggered" + event.toString());
    }

    @Override
    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        logger.info("beforeNodeLeft" + event.toString());
    }

    @Override
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        logger.info("afterNodeLeft" + event.toString());
    }

    @Override
    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        logger.info("beforeVariableChanged" + event.toString());
    }

    @Override
    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        logger.info("afterVariableChanged" + event.toString());
    }
}
