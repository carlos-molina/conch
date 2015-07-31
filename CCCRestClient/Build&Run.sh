#!/bin/sh
# run RestCient
exec mvn clean assembly:single
exec java -cp target/CCCRestClient-1-jar-with-dependencies.jar  uk.ac.ncl.client.RESTClient /Users/alpac/DEV/CCC-new/conch/CCCRestClient/SeqIncorrectCanc-xml