FROM jenkins:1.609.3

# Install maven - required for tutorial jobs
USER root
RUN apt-get update
RUN apt-get install -y maven
USER jenkins

# Install plugins
COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

# Copy configuration scripts that will be executed by groovy
COPY *.groovy /usr/share/jenkins/ref/init.groovy.d/
