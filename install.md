1. Clone conch repo https://github.com/gsfyrakis/conch (Branch cccv2)
2. install mysql 5.6 https://dev.mysql.com/downloads/mysql/5.6.html#downloads
3. download jboss-eap-6.4 https://developers.redhat.com/download-manager/file/jboss-eap-6.4.0.GA-installer.jar or jboss as 7.1.1 http://download.jboss.org/jbossas/7.1/jboss-as-7.1.1.Final/jboss-as-7.1.1.Final.zip
4. install jboss-eap ➜ choose admin password
5. copy conch/JBOSS-EAP-6.4 configuration/mysql folder to jboss-eap-6.4/modules/com
6. add datasource and driver configuration in jboss-eap-6.4/standalone/configuration/standalone-full.xml file

`<datasource jta="false" jndi-name="java:jboss/datasources/RopeDS" pool-name="RopeDS" enabled="true" use-ccm="false">
                    <connection-url>jdbc:mysql://127.0.0.1:3306/rope_historical</connection-url>
                    <driver-class>com.mysql.jdbc.Driver</driver-class>
                    <driver>mysql</driver>
                    <security>
                        <user-name>rope</user-name>
                        <password>Me!Uj4^w</password>
                    </security>
                    <validation>
                        <validate-on-match>false</validate-on-match>
                        <background-validation>false</background-validation>
                    </validation>
                    <statement>
                        <share-prepared-statements>false</share-prepared-statements>
                    </statement>
                </datasource>
                <drivers>
                    <driver name="mysql" module="com.mysql"/>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>`

6. copy conch/JBOSS-EAP-6.4 configuration/drools to  jboss-eap-6.4/standalone/ to install default contract
7. edit change-set.xml in  jboss-eap-6.4/standalone/drools/upload with full path to Rule.drl   
8. `export JBOSS_HOME=<installation` path for jboss-eap-6.4>
9. execute `conch/run.sh` script 
10. check for any exceptions
11. install maven 3.3.9
12. create `.mavenrc` file in user folder 
13. add  configuration to `.mavenrc`  file
`JAVA_HOME='/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home'`
`JAVA_OPTS=“-Xms512m -Xmx1024m -Xss512k -XX:PermSize=64m -XX:MaxPermSize=128m”`

14. copy `conch/JBOSS-EAP-6.4/settings.xml` to `~/.m2/`
15. follow the tutorial to adjust the configuration of the maven jboss-as plugin, if it is needed, in order to deploy the `CCC-Rest-ear.ear` archive to jboss server after each maven build.  http://mastertheboss.com/jboss-frameworks/maven-tutorials/jboss-maven/maven-jboss-as-7-plugin-tutorial
The configuration for the maven jboss-as plugin is on the `conch/pom.xml` file.

Another way to manually deploy the CCC-Rest-ear.ear archive is by visiting the management console of jboss: http://www.mastertheboss.com/jboss-server/jboss-deploy/how-to-deploy-an-application-remotely-with-jboss-as  

16. execute in conch folder ➞ `mvn clean package jboss-as:deploy`
17. check jboss logs for any exceptions
    15:45:22,446 INFO  [org.jboss.as.server] (management-handler-thread - 1) JBAS015859: Deployed “CCCRest-ear.ear” (runtime-name : “CCCRest-ear.ear"
18. `http://localhost:8080/CCCRest-ear-web/UploadForm.html` to upload a new set of rules to the CCC
19. build client and run examples using client app under `conch/CCCRestClient/runClient.sh` (change path to sequence events) 
