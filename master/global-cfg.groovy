import hudson.model.*
import jenkins.model.*
import java.net.InetAddress

//println "--> disabling master executors"
//Jenkins.instance.setNumExecutors(0)

println "--> setting quite period to 3"
Jenkins.instance.setQuietPeriod(3)

println "--> setting checkout retry to 3"
Jenkins.instance.setScmCheckoutRetryCount(3)

// Change it to the DNS name if you have it
println "--> setting jenkins root url"
def ip = InetAddress.localHost.getHostAddress()
jlc = JenkinsLocationConfiguration.get()
jlc.setUrl("http://$ip:8080")
jlc.save()
