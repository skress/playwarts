name := "playwarts"

version := "0.7"

organization := "org.danielnixon"

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("https://github.com/danielnixon/playwarts"))

pomExtra := (
  <scm>
    <url>git@github.com:danielnixon/playwarts.git</url>
    <connection>scm:git:git@github.com:danielnixon/playwarts.git</connection>
  </scm>
  <developers>
    <developer>
      <id>danielnixon</id>
      <name>Daniel Nixon</name>
      <url>https://danielnixon.org/</url>
    </developer>
  </developers>)

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

scalaVersion := "2.11.7"

resolvers += Resolver.sonatypeRepo("releases")

val playVersion = "2.4.3"

libraryDependencies ++= Seq(
  "org.brianmckenna" %% "wartremover" % "0.14",
  "org.scalatest" %% "scalatest" % "2.2.4" % Test,
  "com.typesafe.play" %% "play" % playVersion % Test,
  "com.typesafe.play" %% "play-slick" % "1.0.1" % Test,
  "com.typesafe.play" %% "play-jdbc" % playVersion % Test)

exportJars := true
