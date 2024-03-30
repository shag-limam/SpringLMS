# Utiliser une image de base avec OpenJDK et Tomcat
FROM tomcat:9.0-jdk11-openjdk-slim

# Supprimer l'application Tomcat par défaut
RUN rm -rf /usr/local/tomcat/webapps/*

# Copier le fichier WAR de l'application dans le répertoire webapps de Tomcat
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port du serveur Tomcat (par défaut : 8080)
EXPOSE 8091

# Démarrer le serveur Tomcat
CMD ["catalina.sh", "run"]




