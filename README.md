# Class final project - a banking application


Building using 
```mvn clean compile assembly:single```
will output an executable jar containing all dependencies etc.

The database url is defined in resources/config.properties - I've used sqlite while building this, but if you wanted to use a different database driver that's where you would need to change it (and also where you'd define a username/password for your database if you were using a driver that supports it).
The database and tables will be automatically created if they don't exist already when the program is run.