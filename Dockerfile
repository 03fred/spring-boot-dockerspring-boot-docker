# Use uma imagem base do Tomcat com Java 21
FROM tomcat:10.1-jdk21-temurin

# Copie o arquivo WAR da sua aplicação para o diretório webapps do Tomcat
COPY target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/demo.war

# Exponha a porta 8080 do Tomcat
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]