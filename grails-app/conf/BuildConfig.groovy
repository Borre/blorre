grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolution = {
    inherits("global")
    log "error"
    checksums true

    repositories {
        inherits true

        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenLocal()
        mavenCentral()

        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://maven.springframework.org/milestone/"

    }

    dependencies {
    }

    plugins {
        compile ":mongodb:1.3.0"
        runtime ":jquery:1.8.3"
        runtime ":resources:1.2"
        compile ":spring-security-core:1.2.7.3"
        compile ':cache:1.1.1'
        runtime ":twitter-bootstrap:2.3.2"
        runtime ":fields:1.3"
        compile ":gson:1.1.4"
        //runtime ":cache-headers:1.1.5"
        //runtime ":cached-resources:1.0"
        //runtime ":zipped-resources:1.0"

        build ":tomcat:$grailsVersion"
    }
}
