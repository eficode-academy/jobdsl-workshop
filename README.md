# JobDSL workshop

Contains basic Jenkins Docker image setup required for the workshop.

## What is included

TBD

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

* Make sure that you have correct env settings for docker-machine

```shell
  eval $(docker-machine env jobdsl-workshop)
```

## Setup on Linux

Make sure that you have Docker installed. If not then how to is [here](https://docs.docker.com/installation/ubuntulinux/)

## Preparations

* Clone this repo or transfer existing one into virtual machine using docker-machine scp

```shell
  git clone https://github.com/praqma-training/jobdsl-workshop.git
```

* Kick containers
 
```shell
   cd jobdsl-workshop
   bash -ex do-things.sh
```

* After few seconds you should be able to see Jenkins page in your browser at localhost:8080! Time to do things!

## Assignments

* Follow [JobDSL tutorial](https://github.com/jenkinsci/job-dsl-plugin/wiki/Tutorial---Using-the-Jenkins-Job-DSL) and create simple job
* Learn [how to build your JobDSL specification](https://github.com/jenkinsci/job-dsl-plugin/wiki/User-Power-Moves#run-a-dsl-script-locally) and setup CI job for it
* Learn how to use configure block
* Try templates and libraries
* Team work
