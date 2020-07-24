cd /d "%~dp0"
set WSDL_URL=http://form292:8080/ws/tva?wsdl
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_181
"%JAVA_HOME%\bin\wsimport" -Xnocompile -keep -d src/main/java  %WSDL_URL%
pause