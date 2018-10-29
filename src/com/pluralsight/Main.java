package com.pluralsight;


import jdk.nashorn.internal.runtime.ECMAException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        start();
    }



    public static void start() {
        //keeping the main process clean
        try {
            BankAccount acct1 = new BankAccount("1234", 500);
            acct1.depostit(250);
            saveAccount(acct1, "account.dat");
            BankAccount acct2 = loadAccount("account.dat");
            System.out.println(acct2.getBalance());

        } catch (Exception e){System.out.println(e.getMessage() + e.getCause());}
    }

    private static void saveAccount(BankAccount ba, String filename) throws Exception {
        //Setting up the serialization of objects via Object stream
        try( ObjectOutputStream objectStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)));){
            objectStream.writeObject(ba); //just give it the ref of the Obj to serialize
        }catch (IOException e){System.out.println(e.getMessage() + e.getCause());}

    }

    private static BankAccount loadAccount(String filename){
        BankAccount baLoad = null;
        try(ObjectInputStream objectStream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))){
            baLoad = (BankAccount) objectStream.readObject();
        }catch (IOException e){System.out.println(e.getMessage() + e.getCause());
        }catch (ClassNotFoundException e2){System.out.println(e2.getMessage() + e2.getCause());}

        return baLoad;
    }


    public static void defaultMethod() {
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");

    }
}

