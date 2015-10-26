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

//Define a job name
def jobName = "build-JobDSL-jar"

//Instantiate a new project
FreeStyleProject project = new FreeStyleProject(parent, jobName)

// Define SCM
List<BranchSpec> branches = Collections.singletonList(new BranchSpec("master"))
List<UserRemoteConfig> repoList = new ArrayList<UserRemoteConfig>()
repoList.add(new UserRemoteConfig("https://github.com/jenkinsci/job-dsl-plugin.git", "origin", "", null))
GitSCM gitSCM = new GitSCM(repoList, branches, false, null, null, null, null);

Shell shell = new Shell(
'''
./gradlew :job-dsl-core:oneJar
cp $(find job-dsl-core -name '*standalone.jar'|tail -1) .
''')

ArtifactArchiver artifactArchiver = new ArtifactArchiver("*.jar")
artifactArchiver.setAllowEmptyArchive(false)
artifactArchiver.setOnlyIfSuccessful(true)

project.setScm(gitSCM)
project.getBuildersList().add(shell)
project.getPublishersList().add(artifactArchiver)

project.save()

//Define a seed job name
def seedJobName = "seedjob"

//Instantiate a new project
def seedProject = new FreeStyleProject(parent, seedJobName);
def jobDslBuildStep = new ExecuteDslScripts(
'''
job('test') {
  steps {
    shell('sleep 5')
  }
}
''');

seedProject.getBuildersList().add(jobDslBuildStep);
seedProject.save()

parent.reload()

