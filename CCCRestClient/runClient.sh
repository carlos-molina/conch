#!/bin/sh
# 1. assemble jar with dependencies 
# 2. execute jar with sequences
# Build the CCC client that comm with the CCC server
mvn clean package assembly:single  && 
java -jar target/CCCRestClient-1-jar-with-dependencies.jar /Users/alpac/DEV/conch/CCCRestClient/dataseller-pmlExecSeq-xml/
# Stars the CCC client which starts sending events tio the
# CCC server. The events are in ExecSequencesSamples file. We
# created then with PROMELA
#java -jar target/CCCRestClient-1-jar-with-dependencies.jar /Users/carlosmolina/local/git/conch/CCCRestClient/ExecSequencesSamples/ccTestSeq-xml
