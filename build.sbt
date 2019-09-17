name := """library_app"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

libraryDependencies ++= Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test,
    "mysql" % "mysql-connector-java" % "6.0.6"
)

enablePlugins(FlywayPlugin)

import com.typesafe.config.ConfigFactory

val conf = ConfigFactory.parseFile(new File("./conf/application.conf"))

flywayUrl := conf.getString("db.default.url")
flywayUser := conf.getString("db.default.user")
flywayPassword := conf.getString("db.default.password")
flywayLocations := Seq(
    "filesystem:./conf/db/migration/default"
)

flywayUrl in Test := conf.getString("test.db.default.url")
flywayUser in Test := conf.getString("test.db.default.user")
flywayPassword in Test := conf.getString("test.db.default.password")
flywayLocations in Test := Seq(
    "filesystem:./conf/db/migration/default"
)
