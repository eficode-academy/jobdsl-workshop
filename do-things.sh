docker-compose -p sandbox build
docker-compose -p sandbox up -d
docker-compose -p sandbox scale jslave=3

# Ugly update of master url - required by docker. Could be avoided by using DNS name instead of IP
MASTER_IP=$(docker inspect --format='{{.NetworkSettings.IPAddress}}' sandbox_jmaster_1)
cat << EOF > update-url.groovy
import hudson.model.*
import jenkins.model.*
println "--> setting jenkins root url"
jlc = JenkinsLocationConfiguration.get()
jlc.setUrl('http://${MASTER_IP}:8080')
jlc.save()
EOF
sleep 45
curl --data-urlencode "script=$(<./update-url.groovy)" http://$(docker-machine ip default):8080/scriptText
rm update-url.groovy
