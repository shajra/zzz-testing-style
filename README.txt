Mentoring Testing Style
=======================

This is a small project designed to illustrate concepts involved with testing.
It was presented to university students in a 30 minute session.


Navigating Source Code
----------------------

This project was developed with IntelliJ's IDEA IDE [1] Setting up IDEs,
though, is a real chore.  For learning, just navigate the project with your
favorite text editor.

You'll find all the source for both the main projects in the src/.  The systems
under test are under src/main/.  And the tests are under src/test/.

There are two systems under test:

    - a NumberWords java class that converts numbers to English representations

    - a tic tac toe game implementation

[1] http://www.jetbrains.com/idea/download/


Requirements
------------

Most of the code in this project is straight Java, but a small amount of test
code is written in Scala [2].  Because of the Scala code, I've chosen to use
Simple Build Tool (SBT) [3] to manage the build process.  So to build and run
the tests in this project, you'll need two applications.

    - Java SE JDK 6 (or higher) [4 or 5]
    - SBT launcher JAR v0.7.5.RC0 (or higher) [6]

SBT is minimal bootstrap, which downloads everything it needs from the
internet, so you'll need a network connection to run it.

[2] http://www.scala-lang.org/
[3] http://code.google.com/p/simple-build-tool
[4] http://www.oracle.com/technetwork/java/javase/downloads/index.html
[5] if you're on a Debian-based OS: apt-get install sun-java5-jdk
[6] http://code.google.com/p/simple-build-tool/downloads/list


Running Tests
-------------

After installing the JDK and saving the SBT launcher somewhere, you can run all
the tests with a simple command:

    java -jar $PATH_TO_SBT_LAUNCHER_JAR update test

You'll see a lot of stuff fly, and if everything goes well the last line
printed should be

    [success] Build completed successfully.

If you scroll up, you should see some reporting for all the tests.


A Little About SBT
------------------

All of what the SBT launcher JAR downloads end up in two places:

    - in the project itself under lib_managed/ and project/

    - in $HOME/.ivy2/

The SBT invocation above runs two actions: 'update' and 'test'.  The update
action downloads dependencies from the internet.  The 'test' action performs
all the compilation necessary and runs then all the tests.

Compiled Java classes end up in the target/ directory.  You can run a 'clean'
action, which more or less just deletes target/.  There are a lot of other
actions; use the 'actions' action to get a listing.

Running SBT can appear slow because the JVM takes a lot of time to startup.  To
get around this problem you can run SBT with no actions and SBT will run as an
interactive shell, keeping the JVM warm and running in the background.

That should be enough information to demystify SBT.  SBT is an interesting
tool, and you can learn more from the official documentation [6].

[6] http://code.google.com/p/simple-build-tool/wiki/DocumentationHome


Configuring IntelliJ IDEA
-------------------------

If you're really interested in using an IDE, IDEA to date has the most modern
support for Scala, SBT, and arguably Java too.  JetBrains provides a free
"Community Edition" of IDEA that has no restrictions relevant to this project.

You will, though, want to install two freely available plugins, though:

    - SBT
    - Scala

IDEA comes with a builtin plugin manager you can use to install these.  Note,
the "Scala Power Pack" plugin is deprecated and unmaintained.  Also, you will
have to configure the SBT plugin to point to your downloaded sbt-launcher JAR.

Finally, to fully integrate the project into IDEA, from the command-line, run

    java -jar $PATH_TO_SBT_LAUNCHER_JAR idea

which will run an SBT plugin that generates all the IDEA-specific configuration
files needed:

    - .idea
    - mentoring-testing-style.iml
    - project/project.iml

Once these files are created, open the project fresh in IDEA, and you should
have all the IDE pleasantries in both the Java and Scala parts of the project.
