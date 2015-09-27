# JobDSL workshop base repo

Contains basic Jenkins Docker image setup required for the workshop.

## What is included

* Dockerfile - Thin docker build file. All magic is done in [Jenkins official build file](https://github.com/jenkinsci/docker/tree/9395d3fdd74cd43f03b1844fbb0c3e48d713cbc1). I'm only providing list of plugins to install and few groovy scripts for initial configuration 
* plugins.txt - list of plugins that will be automatically installed. All versions of the plugins fixed as well as Jenkins version in order to ensure reproducibility of the setup as time goes.
  * [job-dsl](https://wiki.jenkins-ci.org/display/JENKINS/Job+DSL+Plugin) - JobDSL plugin
* quietperiod.groovy - set global Quiet Period to 0
* executors.groovy - set number of executors on master to 0


## Setup on Mac OS and Windows

* Create new virtual machine using [docker-machine](https://docs.docker.com/installation/mac/)

```shell
  docker-machine create --driver virtualbox jobdsl-workshop
```

* Setup port forwarding in order to be able to access web pages from Docker container in browser on the host machine

```shell
  VBoxManage controlvm jobdsl-workshop natpf1 "HTTP,tcp,127.0.0.1,8080,,8080"
```  

* Jump into virtual machine by running

```shell
  docker-machine ssh jobdsl-workshop
```

* Clone this repo or transfer existing one into virtual machine using docker-machine scp

```shell
  git clone https://github.com/Praqma/jobdsl-workshop-base.git
```

* Build docker image

```shell
  cd jobdsl-workshop
  docker build -t jobdsl-workshop .
```

* Kick off new container
 
```shell
  cd jobdsl-workshop
  docker run -p 8080:8080 jobdsl-workshop
```

* After few seconds you should be able to see Jenkins page in your browser at localhost:8080! Time to do things!

* Follow assignments from [JonDSL workshop GitHub Classroom](https://classroom.github.com/organizations/644249-jobdsl-workshop)
