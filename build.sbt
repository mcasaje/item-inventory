name := "item-inventory"

version := "1.0"

lazy val `item-inventory` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"


unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

// Enables Guice bindings with routes
routesGenerator := InjectedRoutesGenerator

// Set to 'false' when deploying
// https://www.playframework.com/documentation/2.4.x/JavaJPA#Deploying-Play-with-JPA
PlayKeys.externalizeResources := false

libraryDependencies ++= Seq(javaJdbc, cache, javaWs)

libraryDependencies += "junit" % "junit" % "4.11"

// MySQL Database, Hibernate, JPA
libraryDependencies ++= Seq(
  javaJpa,
  evolutions,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.9.Final",
  "org.hibernate.javax.persistence" % "hibernate-jpa-2.1-api" % "1.0.0.Final",
  "mysql" % "mysql-connector-java" % "5.1.18"
)

// Java Simplified Encryption
libraryDependencies += "org.jasypt" % "jasypt" % "1.9.2"
