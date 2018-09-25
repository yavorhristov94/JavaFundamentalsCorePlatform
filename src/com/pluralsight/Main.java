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


        String filename = readArguments(args);
        if(!Files.exists(Paths.get(filename))){System.out.println("\n File not found: "+filename);
        return;} else showFileLines(filename);

        fixFileName(args);
        //Take argument
        //Check for length of argument
        //If longer than 1 word, check for quotes
        //If no quotes, add them
    }

    public static void defaultMethod(){
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");
    }
    public static String readArguments(String[] args){
        boolean longArg = false;
        String fn = null;
        String argument = null;
        if(args.length<1){showUsage();}
        if(args.length >= 2){longArg = true;}else return fn = args[0];




        return fn;
    }

    public static void fixFileName(String[] args){
        //Check if filename has multiple spaces
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
