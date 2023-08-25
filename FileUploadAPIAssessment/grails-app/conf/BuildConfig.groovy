grails.servlet.version = "3.0"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.fork = [
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven"
grails.project.dependency.resolution = {
    inherits("global") {
        // specify dependency exclusions here
    }
    log "error"
    checksums true

    repositories {
        inherits true
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
        mavenLocal()

        mavenRepo 'https://grails.jfrog.io/grails/plugins/'
        mavenRepo 'https://grails.jfrog.io/grails/core/'
        mavenRepo "https://repo1.maven.org/maven2/"
        mavenRepo 'https://artifacts.alfresco.com/nexus/content/repositories/public/'
        mavenRepo 'http://maven.lolay.com/'
    }

    dependencies {
        runtime 'mysql:mysql-connector-java:5.0.7'
    }

    plugins {
        build ":tomcat:7.0.47"
        compile ":scaffolding:2.0.1"
        compile ':cache:1.1.1'
        runtime ":cors:1.3.0"
        runtime ":hibernate:3.6.10.6"
        runtime ":jquery:1.10.2.2"
        runtime ":resources:1.2.1"
        compile ":webxml:1.4.1"
    }
}
