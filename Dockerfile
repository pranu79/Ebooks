# Stage 1: Build the WAR using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build WAR file
RUN mvn clean package -DskipTests

# Stage 2: Deploy WAR to Tomcat
FROM tomcat:9.0-jdk17-temurin

# Disable shutdown port in server.xml
RUN sed -i 's/port="8005"/port="-1"/' /usr/local/tomcat/conf/server.xml

# Ensure Tomcat uses the Render/Heroku dynamic port if available, otherwise 8080
ENV PORT=8080

# DO NOT need this, because your app is not replacing ROOT
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy WAR into Tomcat webapps (keeps name for context path)
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Run Tomcat in foreground (required for Render, otherwise container exits)
CMD ["catalina.sh", "run"]