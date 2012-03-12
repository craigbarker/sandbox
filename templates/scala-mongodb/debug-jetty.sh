export MAVEN_OPTS="-XX:MaxPermSize=512m -Xmx1024m -Xms256m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
mvn -o -f webapp/pom.xml jetty:run -Denv=dev
