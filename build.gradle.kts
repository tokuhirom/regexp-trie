plugins {
    id("java")
    id("maven-publish")
    id("com.github.spotbugs") version "5.0.14"
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

version = System.getenv("LIB_VERSION")

group = "me.geso"

description = """regexp-trie"""

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

repositories {
     mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set(project.name)
                packaging = "jar"
                if (project.description != null) {
                    description.set(project.description)
                } else {
                    description.set("Trie based regexp generator")
                }
                url.set("https://github.com/tokuhirom/regexp-trie")

                scm {
                    url.set("scm:git@github.com:tokuhirom/regexp-trie.git")
                    connection.set("scm:git@github.com:tokuhirom/regexp-trie.git")
                    developerConnection.set("scm:git@github.com:tokuhirom/regexp-trie.git")
                }
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://www.opensource.org/licenses/mit-license.php")
                    }
                }
                developers {
                    developer {
                        id.set("tokuhirom")
                        name.set("Tokuhiro Matsuno")
                        email.set("tokuhirom@gmail.com")
                    }
                }
            }
        }
        repositories {
            maven {
                url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
    }
}
