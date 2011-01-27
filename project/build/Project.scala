import sbt._


class Project(info: ProjectInfo) extends DefaultProject(info) with IdeaProject {

  val specs = "org.scala-tools.testing" %% "specs" % "1.6.7.2" % "test" withSources()

  val junit = "junit" % "junit" % "4.8.1" % "test"

  val junitInterface = "com.novocode" % "junit-interface" % "0.5" % "test"

  // override def defaultLoggingLevel = Level.Debug

}
