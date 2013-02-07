#!/bin/sh
# run standalone Jboss 7.1.1
exec $JBOSS_HOME/bin/standalone.sh  --server-config=standalone-full-ha.xml
