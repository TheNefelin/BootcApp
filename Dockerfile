FROM tomcat:10.1-jdk21

WORKDIR /usr/local/tomcat/webapps/

COPY target/BootcApp-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
