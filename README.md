# JobDSL workshop

Contains basic Jenkins Docker image setup required for the workshop.

## What is included

* Dockerfile - Thin docker build file. All magic is done in [Jenkins official build file](https://github.com/jenkinsci/docker/tree/9395d3fdd74cd43f03b1844fbb0c3e48d713cbc1). I'm only providing list of plugins to install and few groovy scripts for initial configuration 
* plugins.txt - list of plugins that will be automatically installed. All versions of the plugins fixed as well as Jenkins version in order to ensure reproducibility of the setup as time goes.
  * [job-dsl](https://wiki.jenkins-ci.org/display/JENKINS/Job+DSL+Plugin) - JobDSL plugin
  * [copyartifact](https://wiki.jenkins-ci.org/display/JENKINS/Copy+Artifact+Plugin) - Copy artifact plugin. Required for creation of CI job as well as next one
  * [git](https://wiki.jenkins-ci.org/display/JENKINS/Git+Plugin) - Git plugin.
  * [build-timeou](https://wiki.jenkins-ci.org/display/JENKINS/Build-timeout+Plugin) - Build Timeout plugin. Used in the library example
  * Dependencies for the Build Timeout plugin
    * token-macro
  * [timestamper](https://wiki.jenkins-ci.org/display/JENKINS/Timestamper) - Timestamper plugin. Adds timestamps to console output. Used in the library example
  * Dependencies for Git plugin
    * credentials
    * git-client
    * scm-api
    * mailer
    * matrix-project
    * ssh-credentials
* createjob.groovy - will create job required to prebuild JobDSL jar file

## Setup on Mac OS and Windows

* Create new virtual machine using [docker-machine](https://docs.docker.com/installation/mac/)

```shell
  docker-machine create --driver virtualbox jobdsl-workshop
```

* Setup port forwarding in order to be able to access web pages from Docker container in browser on the host machine

```shell
  VBoxManage controlvm jobdsl-workshop natpf1 "HTTP,tcp,127.0.0.1,8080,,8080"
```  

* Restart virtual machine in order to make sure that port forwarding settings applied correctly. *Important!* Check that you don't have other VM's running that uses the same port (run docker-machine ls or check VirtualBox GUI)

```shell
  docker-machine restart jobdsl-workshop
```

* Jump into virtual machine by running

```shell
  docker-machine ssh jobdsl-workshop
```

## Setup on Linux

Make sure that you have Docker installed. If not then how to is [here](https://docs.docker.com/installation/ubuntulinux/)

## Preparations

* Clone this repo or transfer existing one into virtual machine using docker-machine scp

```shell
  git clone https://github.com/praqma-training/jobdsl-workshop.git
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

## Assignments

* Follow [JobDSL tutorial](https://github.com/jenkinsci/job-dsl-plugin/wiki/Tutorial---Using-the-Jenkins-Job-DSL) and create simple job
* Learn [how to build your JobDSL specification](https://github.com/jenkinsci/job-dsl-plugin/wiki/User-Power-Moves#run-a-dsl-script-locally) and setup CI job for it
* Learn how to use configure block
* Try templates and libraries
* Team work
