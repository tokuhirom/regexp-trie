//buildscript {
//  repositories {
//    mavenCentral()
//  }
//  dependencies {
//    classpath "com.adaptc.gradle:nexus-workflow:0.6"
//  }
//}

plugins {
    id("java")
    id("com.github.spotbugs") version "5.0.14"
}
//apply plugin: "maven"

//apply plugin: "nexus-workflow"

project.version = "1.0.5"

group = "me.geso"
//archivesBaseName = "regexp-trie"

description = """regexp-trie"""

//[compileJava, compileTestJava]*.options*.encoding = "UTF-8"
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
     mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.11")
}

/*
findbugsMain {
  ignoreFailures = false
  effort = "max"
}
 */

//task javadocJar(type: Jar) {
//    classifier = "javadoc"
//    from javadoc
//}
//
//task sourcesJar(type: Jar) {
//    classifier = "sources"
//    from sourceSets.main.allSource
//}
//
//artifacts {
//    archives javadocJar, sourcesJar
//}

//if (project.hasProperty("ossrhUsername")) {
//  try {
//    uploadArchives {
//      repositories {
//        mavenDeployer {
//          repository(url: "https://oss.sonatype.org/service/local/staging/deploy/maven2/") {
//            authentication(userName: ossrhUsername, password: ossrhPassword)
//          }
//
//          snapshotRepository(url: "https://oss.sonatype.org/content/repositories/snapshots/") {
//            authentication(userName: ossrhUsername, password: ossrhPassword)
//          }
//
//          pom.project {
//            name "regexp-trie"
//            packaging "jar"
//            // optionally artifactId can be defined here
//            description "Trie based regexp generator"
//            url "https://github.com/tokuhirom/regexp-trie/"
//
//            scm {
//              url "https://github.com/tokuhirom/regexp-trie/"
//              connection "scm:git:git://github.com/tokuhirom/regexp-trie.git"
//              developerConnection "scm:git:git@github.com:tokuhirom/regexp-trie.git"
//            }
//
//            licenses {
//              license {
//                name "MIT License"
//                url "https://www.opensource.org/licenses/mit-license.php"
//              }
//            }
//
//            developers {
//              developer {
//                id "tokuhirom"
//                name "Tokuhiro Matsuno"
//                email "tokuhirom@gmail.com"
//              }
//            }
//          }
//        }
//      }
//    }
//  } catch (MissingPropertyException mpe) {
//    if (System.env["CI"]) {
//      println("Run on CI");
//    } else {
//      throw mpe;
//    }
//  }
//}