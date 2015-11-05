import hudson.model.*
import jenkins.model.*

println "--> disabling master executors"
Jenkins.instance.setNumExecutors(0)

println "--> setting quite period to 3"
Jenkins.instance.setQuietPeriod(3)

println "--> setting checkout retry to 3"
Jenkins.instance.setScmCheckoutRetryCount(3)

// Uncomment text below if you have a DNS name
//println "--> setting jenkins root url"
//jlc = JenkinsLocationConfiguration.get()
//jlc.setUrl('http://my-sandbox.com')
//jlc.save()import jenkins.model.*
