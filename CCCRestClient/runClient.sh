#!/bin/sh
# 1. assemble jar with dependencies 
# 2. execute jar with sequences
mvn clean package assembly:single  && 
java -jar target/CCCRestClient-1-jar-with-dependencies.jar /Users/alpac/Documents/JAVA-PROJECTS/conch/CCCRestClient/ExecSequencesSamples/ccTestSeq-xml/