package com.pluralsight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

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
        Properties props = new Properties();
        props.setProperty("displayName", "Yavor Hristov");
        String name = props.getProperty("displayName");
        System.out.println(name);

        storer();
        xmlStorer();
        xmlLoader();
    }

    public static void storer(){
        Properties props = new Properties();
        props.setProperty("accountNumber", "123-45-6789");
        try (Writer writer = Files.newBufferedWriter(Paths.get("xyz.properties"))){
            props.store(writer, "My comment");
            //To store we pass in the writer that we wanna use
            //If we dont want a comment, we pass in null
            //This stores the FULL map of properties
        } catch (Exception e){}
    }

    public static void storerLoader(){
        //to add functionality as per video
        //add 4 values manually and load it in
        Properties props = new Properties();
        try (Reader reader = Files.newBufferedReader(Paths.get("myapp.properties"))){

        } catch (Exception e){}
    }

    public static void xmlStorer(){
        Properties props = new Properties();
        props.setProperty("displayName", "Yavor Hristov");
        props.setProperty("accountNumber", "123-45-6789");

        try (OutputStream out = Files.newOutputStream(Paths.get("props.xml"))){
            props.storeToXML(out, "My comment");
            //Note, as it's to XML we are using an output stream, not a textual writer
        } catch (Exception e){}

    }

    public static void xmlLoader(){
        Properties props = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("props.xml"))){
            props.loadFromXML(in);
            //Note, as it's to XML we are using an output stream, not a textual writer
        } catch (Exception e){}

        String val1 = props.getProperty("displayName");
        String val2 = props.getProperty("accountNumber");
        System.out.println(val1+" "+val2);
    }


    public static void defaultMethod() {
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");

    }
}

