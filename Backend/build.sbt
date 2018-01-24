import NativePackagerKeys._

maintainer:= "Erik v. Slingerland"
dockerExposedPorts in Docker := Seq(9000, 9443)

name := """WtBackend"""
organization := "web.verocode.de"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.45"
libraryDependencies += "com.auth0" % "java-jwt" % "3.3.0"

libraryDependencies += "com.lightbend.play" %% "play-socket-io" % "1.0.0-beta-2"
libraryDependencies += "org.projectlombok" % "lombok" % "1.12.6"
libraryDependencies += "com.google.api-client" % "google-api-client" % "1.23.0"
