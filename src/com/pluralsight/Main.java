package com.pluralsight;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

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
//        if(args.length <1) {
//            System.out.println("No arguments provided");
//            showUsage();
//            return;
//        } else {
//            for (String word : args) System.out.println(word);
//            //If ran from cmdline with java.com.pluralsight.Main Hello world
//            //this would print Hello word (on two separate rows)
//        String filename = args[0];
//        if(!Files.exists(Paths.get(filename))){System.out.println("\n File not found: "+filename);
//        return;} else showFileLines(filename);
//        }


        fixFileName(args);
    }

    public static void defaultMethod(){
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");
    }

    public static void fixFileName(String[] args){
        //Check if filename has multiple spaces
        if (args.length >= 1) {
            StringBuilder fixer = new StringBuilder();
            for (String word : args){
                fixer.append(word);
                fixer.append(" ");
                /* EXAMPLE
                Args: my file name
                It puts each of them together and a space, so the result is "my file name "
                 */
            }
            fixer.setLength(fixer.length() - 1); //remove the last, unneeded space
            System.out.println(fixer.toString());
        }


    }
    public static void showFileLines (String filename){
        System.out.println();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename))){
            String line = null;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (Exception e1){System.out.println(e1.getClass().getSimpleName());}
    }

    public static void showUsage(){
        System.out.println();
        System.out.println("Please provide a filename to process on the command line");
    }
}
