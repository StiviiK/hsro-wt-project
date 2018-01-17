name := """WtBackend"""
organization := "web.verocode.de"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.45"
libraryDependencies += "com.auth0" % "java-jwt" % "3.3.0"
