FROM java:8u45-jdk

# Add support for proxies.
# Values should be passed as build args
# http://docs.docker.com/engine/reference/builder/#arg
ENV http_proxy ${http_proxy:-}
ENV https_proxy ${https_proxy:-}
ENV no_proxy ${no_proxy:-}
ENV HTTP_PROXY ${HTTP_PROXY:-}
ENV HTTPS_PROXY ${HTTPS_PROXY:-}
ENV NO_PROXY ${NO_PROXY:-}

ENV JENKINS_SWARM_VERSION 2.0
ENV HOME /home/jenkins-slave
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# install netstat to allow connection health check with
# netstat -tan | grep ESTABLISHED
RUN apt-get update && apt-get install -y net-tools && rm -rf /var/lib/apt/lists/*

RUN useradd -c "Jenkins Slave user" -d $HOME -m jenkins-slave
RUN curl --create-dirs -sSLo /usr/share/jenkins/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar http://maven.jenkins-ci.org/content/repositories/releases/org/jenkins-ci/plugins/swarm-client/$JENKINS_SWARM_VERSION/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar \
  && chmod 755 /usr/share/jenkins

COPY start.sh /usr/local/bin/start.sh
RUN chown jenkins-slave /usr/local/bin/start.sh
RUN chmod 755 /usr/local/bin/start.sh

# Install tools
RUN apt-get update && apt-get install -y maven git

USER jenkins-slave
VOLUME /home/jenkins-slave

ENTRYPOINT ["/usr/local/bin/start.sh"]