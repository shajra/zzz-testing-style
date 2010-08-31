import sbt._


class Project(info: ProjectInfo) extends DefaultProject(info) { // with IdeaProject {

  val specs = "org.scala-tools.testing" %% "specs" % "1.6.5" % "test" withSources ()
  val junit = "junit" % "junit" % "4.8.1" % "test"
  val junitInterface = "com.novocode" % "junit-interface" % "0.4" % "test"
  // val jdave = "org.jdave" % "jdave-junit4" % "1.1" % "test"

  // val jdaveRepo = "Laughing Panda" at "http://www.laughingpanda.org/maven2/"

  // override def defaultLoggingLevel = Level.Debug

}
