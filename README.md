# Class final project - a banking application

### A new branch for further development after the project
(Because apparently I can't fork a project I already own?)

Mainly because I keep thinking of things that I wanted to try or different ways to do things, and I just can't leave things alone.

So this isn't part of the handed-in project, I've stopped work on that (on the master branch at v1) for hand-in, this is just for my own personal development/amusement/whatever.

And I might finally work out how to do that logo...


### Building
Application uses Maven to manage dependencies (mostly JavaFX and sqlite), so it should build fine in any IDE that works with that, or fom the command line using Maven.

Building using 
```mvn clean compile assembly:single```
will output an executable jar containing all dependencies etc.

The database url is defined in resources/config.properties - I've used sqlite while building this, but if you wanted to use a different database driver that's where you would need to change it (and also where you'd define a username/password for your database if you were using a driver that supports it).

The database and tables will be automatically created if they don't exist already when the program is run (if you're using a different database driver you might also want to check the commands in resources/sql_table_setup.txt in case there's anything sqlite-specific (like having to drop each table separately) that your database does differently)..