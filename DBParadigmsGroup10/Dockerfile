FROM tomcat:8.0-jre8

ENV DBIP "redis"

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/DBParaAPI.war /usr/local/tomcat/webapps/server.war

CMD ["catalina.sh", "run"]