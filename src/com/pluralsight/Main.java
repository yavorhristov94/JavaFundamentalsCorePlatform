package com.pluralsight;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Main {

    public static void main(String[] args) {
	// This is the base branch, from which we expand for specific lessons
    //Do NOT initialize values in the Main method unless absolutely necessary
    //Create a method clearly stating the function in its name, in case merging is needed
        //List of branches:
        /*
        master
        TryWithResources


         */

        defaultMethod();
    }

    public static void defaultMethod(){
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");
    }

    public static void doTryCatchFinally() {
        char[] buff = new char[8];
        int length;
        Reader reader = null;

        try {
            reader = Helper.openReader("file1.txt")//method to be added later
            while ((length = reader.read(buff)) >= 0) {
                System.out.println("\nlength" + length);
                for (int i = o; i < length; i++) {
                    System.out.println(buff[i]);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e2) {
                System.out.println(e2.getClass().getSimpleName() + " - " + e2.getMessage());
            }
        }

    }

    //By putting the resource in the try statement, we auto-close it
    public static void doTryWithResources(){
        char[] buff = new char[8];
        int length;
        try (Reader reader = Helper.openReader("file1.txt")) {
            while ((length = reader.read(buff)) >= 0) {
                System.out.println("\nlength" + length);
                for (int i = o; i < length; i++) {
                    System.out.println(buff[i]);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }


    public static void doTryWithResourcesMulti(){
        char[] buff = new char[8];
        int length;
        //We can call several resources for auto-closing
        try (Reader reader = Helper.openReader("file1.txt");
             Writer writer = Helper.openWriter("file2.txt")) {
            while ((length = reader.read(buff)) >= 0) {
                System.out.println("\nlength" + length);
                writer.write(buff, 0, length);//what to write, how to offset, how long to write
            }
        }
        catch (IOException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

}
