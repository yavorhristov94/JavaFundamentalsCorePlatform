package com.pluralsight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.*;

public class Main {

    static Logger logger2 = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
    //New way, since it makes it a static value for all and does the whole call in one line


    public static void main(String[] args) {
        // This is the base branch, from which we expand for specific lessons
        //Do NOT initialize values in the Main method unless absolutely necessary
        //Create a method clearly stating the function in its name, in case merging is needed
        //List of branches:
        /*
        master
        TryWithResources
        StringPractice


         */

        defaultMethod();
        start();
    }



    public static void start() {
        //keeping the main process clean
        try {
            //settingLevels();
            //loggingMethods();
//            customRelations();
        } catch (Exception e){}
        codeBasedConfig();
    }


    public static void codeBasedConfig() {
        Handler h = new ConsoleHandler();
        h.setLevel(Level.ALL);
        logger2.addHandler(h);
        logger2.setLevel(Level.ALL);
        logger2.log(Level.ALL, "We're Logging!");

//To now set up the formatting, when we launch it through the console
//we for example need to type in
//java -D java.util.logging.SimpleFormatter.format=%5$s,%2$s,etc... com.appname

//With file based config it'd look like
//java -D java.util.logging.config.file=log.properties com.appname
// (if the file was named that)
    }

    public static void rotatingFileLogs() throws IOException{
        FileHandler h =
                new FileHandler("%h/myapp_%g.log", 1000, 4);
        //output C:\Users\XXX\myapp_0.log ; XXX_1.log etc

        h.setFormatter(new SimpleFormatter());
        logger2.addHandler(h);
    }

    public static void customRelations() {
        Handler h = new ConsoleHandler();
        Formatter f = new SimpleFormatter();
        h.setFormatter(f);
        logger2.addHandler(h);
        logger2.setLevel(Level.INFO);
        logger2.log(Level.INFO, "We're Logging!");
    }

    public static void settingLevels() {
        //old technique
        LogManager lm = LogManager.getLogManager();
        Logger logger = lm.getLogger((Logger.GLOBAL_LOGGER_NAME));
        //the issue is we need to do the above each time we want to add this
        logger.log(Level.INFO, "My first log message");
        logger.log(Level.INFO, "Another message");


        logger2.setLevel(Level.INFO); //Will capture all that is INFO or higher
        logger2.log(Level.SEVERE, "Severe");
        logger2.log(Level.INFO, "Info");
        logger2.log(Level.FINE, "Fine");

        logger2.setLevel(Level.FINE);
    }

    public static void loggingMethods(){
        logger2.severe("Uh oh!"); //can pass in a message just by doing logger.levelmethod

        //precise method - logp
        logger2.logp(Level.SEVERE, "com.pluralsight.Main", "loggingMethods", "It Broke!");
        //The level, the class(with package), the method, the message


        logger2.entering("com.pluralsight.Main", "loggingMethods");
        logger2.exiting("com.pluralsight.Main", "loggingMethods");

        //These WONG show up unless it is set to get to FINER messages

        logger2.log(Level.INFO, "{0} is my favourite", "Java");
        //can put in a specific value to be the parameter

        logger2.log(Level.INFO, "{0} is {1} days from {2}", new Object[]{"wed", 2, "fri"});
        //Can put in an array to attach to the parameters as well
    }

    public static void defaultMethod() {
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");

    }
}

