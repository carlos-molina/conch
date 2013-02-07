Contract Compliance Checker : Readme.txt
----------------------------------------

Prerequisites: 
--------------	

	JBoss 7.1.1, maven 3.0.4, Java 1.6

Notes:
------

 - Use run.sh script to start JBoss : ./run.sh
 - Use maven to clean, build all the projects and deploy to JBoss : mvn clean package jboss-as:deploy
 - Default folder for all the rules files is $JBOSS_HOME/standalone/Drools/upload/
 - Folder JBoss 7.1.1 Configuration : 
 	* contains custom configuration profile in subfolder standalone/configuration for starting JBoss.
 	* mysql module to install on $JBOSS_HOME/modules/ in order to enable support for MySql backend.  It contains module.xml and mysql-connector-java-5.1.19.jar files. Paste the two files to the correct path in JBoss.

Credentials: 
------------
	database name : rope_historical
	username : rope
	password : Me!Uj4^w
	
Build and deploy CCC
--------------------
mvn clean package jboss-as:deploy -Dmaven.test.skip=true -Dmaven.test.failure.ignore=true

