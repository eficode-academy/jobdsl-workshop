import hudson.model.*
import jenkins.model.*
import javaposse.jobdsl.plugin.*
import hudson.tasks.Shell
import hudson.plugins.git.GitSCM
import hudson.plugins.git.BranchSpec
import hudson.plugins.git.UserRemoteConfig
import hudson.tasks.ArtifactArchiver

//Get instance of Jenkins
def parent = Jenkins.getInstance()

//Define a seed job name
def seedJobName = "seedjob"

//Instantiate a new project
def seedProject = new FreeStyleProject(parent, seedJobName);
def jobDslBuildStep = new ExecuteDslScripts(
'''
job('Hello-world') {
  steps {
    shell('echo Hello!')
  }
}
''');

seedProject.getBuildersList().add(jobDslBuildStep);
seedProject.save()

parent.reload()

