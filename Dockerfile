# Use uma imagem base do Maven com Java 21
FROM eclipse-temurin:21-jdk-alpine

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Instale o Maven
RUN apk update && \
    apk add --no-cache maven

# Copie os arquivos pom.xml e src para o diretório de trabalho
COPY pom.xml ./
COPY src ./src/

# Execute o build do Maven para gerar o arquivo WAR
RUN mvn clean package -Dmaven.test.skip=true

# Use uma imagem base do Tomcat com Java 21
FROM tomcat:10.1-jdk21-temurin

# Copie o arquivo WAR gerado pelo Maven para o diretório webapps do Tomcat
COPY --from=0 /app/target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/demo.war

# Exponha a porta 8080 do Tomcat
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]