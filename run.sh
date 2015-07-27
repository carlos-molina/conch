#!/bin/sh
# run standalone Jboss EAP 6.4
exec $JBOSS_HOME/bin/standalone.sh -c standalone-full.xml -b 0.0.0.0
