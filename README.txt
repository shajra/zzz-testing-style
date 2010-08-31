Mentoring Testing Style
=======================

This is a small project designed to illustrate concepts involved with testing.
It was presented to university students in a 20 minute session.


Requirements
------------

Most of the code in this project is straight Java, but a small amount of test
code is written in Scala.  Because of the Scala code, I've chosen to use SBT to
manage the build process.  So to build and run the tests in this project,
you'll need two installations

    - Java SE JDK 6 (or higher) [1 or 2]
    - SBT launcher jar v0.7.4 (or higher) [3]

[1] http://www.oracle.com/technetwork/java/javase/downloads/index.html
[2] if you're on a Debian-based OS: apt-get install sun-java5-jdk
[3] http://code.google.com/p/simple-build-tool/downloads/list


Navigating Source Code
----------------------

This project has .i?? files for IntelliJ's IDEA IDE [4], which this project was
developed with.  Setting up IDEs, though, is a real chore.  For learning, just
navigate the project with your favorite text editor.

You'll find all the source for both the main projects in the "src" directory.
The systems under test are under src/main.  And the tests are under src/test.

There are two systems under test:

    - a NumberWords java class that converts numbers to English representations

    - a tic tac toe game implementation

[4] http://www.jetbrains.com/idea/download/


Running Tests
-------------

After installing the JDK and saving the SBT launcher somewhere run you can run
all the tests with a simple command:

    java -jar $PATH_TO_SBT_LAUNCHER_JAR update test

You'll see a lot of stuff fly and if everything goes well the last line printed
should be

    [success] Build completed successfully.

And if you scroll up, you should see some reporting for all the tests.


A Little About SBT
------------------

The SBT launcher jar is a bootstrap application that goes out to the internet
and downloads everything you really need.  These downloads end up in two
places:

    - in the project itself under lib_managed and project
    - in $HOME/.ivy2.

The SBT invocation above runs two actions: 'update' and 'test'.  The update
action grabs things from the internet.  The test action performs all the
compilation necessary and runs then all the tests.

Compiled Java classes end up in the target directory.

That should be enough information to not be surprised by all the SBT magic.
SBT is an interesting tool, and you can learn more from the official
documentation [5].

[5] http://code.google.com/p/simple-build-tool/wiki/DocumentationHome
