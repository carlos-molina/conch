@echo off
echo Starting JBOSS EAP..............
REM Script to start JBOSS EAP server in windows
call "%JBOSS_HOME%"/bin/standalone.bat -c standalone-full.xml -b 0.0.0.0