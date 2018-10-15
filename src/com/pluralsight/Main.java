package com.pluralsight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
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
//            doAdder();
//            doThreadedAdder();
//            doPooledThreadedAdder();
//            doRelatedAdder();
//            banker();
            transactioner();
        } catch (Exception e){System.out.println(e.getMessage() + e.getCause());}

    }

    public static void transactioner() throws Exception{
        ExecutorService es = Executors.newFixedThreadPool(5);
        BankAccount acc1 = new BankAccount(490);
        BankAccount acc2 = new BankAccount(490);

        TxWorker[] workers = {
                new TxWorker(acc1, 'd', 100),
                new TxWorker(acc1, 'w', 10),
                new TxWorker(acc2, 'd', 10)
        }; //Retrieve TxWorker instances

        for(TxWorker worker:workers) es.submit(worker);

        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);
    }

    public static void banker() throws Exception{
        ExecutorService es = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);
        NonSafeAccount account2 = new NonSafeAccount(100);

//          One thread, will always work, though slow
//        BankWorker worker = new BankWorker(account);
//        es.submit(worker);

//          5 threads working at once - uncertain result
//            for(int i=0;i<5;i++){
//            BankWorker worker = new BankWorker(account);
//            es.submit(worker);    }

        //but with the methods syyycnhed all works fine!
//        for(int i=0;i<5;i++){
//            BankWorker worker = new BankWorker(account);
//            es.submit(worker);}

        //synching inside the method manually also works
        for(int i=0;i<5;i++){
        NonSafeWorker worker2 = new NonSafeWorker(account2);
        es.submit(worker2);}



        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);

    }

    public static void doRelatedAdder() throws Exception,IOException, InterruptedException{
        String[] inFiles = {"./file1.txt", "./file2.txt", "./file3.txt", };
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inFiles.length];
        //future represents a background task, templated on the return type

        for(int i=0; i <inFiles.length; i++){
            AdderCallable adder = new AdderCallable(inFiles[i]);
            results[i] = es.submit(adder);
            //A ref of each Future background task is put into the results array
        }

        for(Future<Integer> result:results){
            int value = result.get(); //blocks until return value available
            System.out.println("Total: "+value);
        }

        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);

    }

    public static void doPooledThreadedAdder() throws IOException, InterruptedException{
        String[] inFiles = {"./file1.txt", "./file2.txt", "./file3.txt", };
        String[] outFiles = {"./file1.out.txt", "./file2.out.txt", "./file3.out.txt", };

        ExecutorService es = Executors.newFixedThreadPool(3);

        for(int i=0; i <inFiles.length; i++){
            Adder adder = new Adder(inFiles[i], outFiles[i]);
            es.submit(adder);
        }
        es.shutdown();
        es.awaitTermination(60, TimeUnit.SECONDS);
    }


    public static void doThreadedAdder() throws IOException, InterruptedException{
        String[] inFiles = {"./file1.txt", "./file2.txt", "./file3.txt", };
        String[] outFiles = {"./file1.out.txt", "./file2.out.txt", "./file3.out.txt", };

        Thread[] threads = new Thread[inFiles.length];

        for(int i=0; i <inFiles.length; i++){
            Adder adder = new Adder(inFiles[i], outFiles[i]);
//            Thread thread = new Thread(adder);
//            This is commented out due to the alternate method we use, to prevent
//            the main thread from closing before the others
            threads[i] = new Thread(adder);
            threads[i].start();
        }
        //And this fires up a thread for each run, for each file

        for(Thread thread:threads){
            thread.join();//this joins ALL the threads to main so that they will be waited for
        }

    }

    public static void doAdder() throws IOException{
        String[] inFiles = {"./file1.txt", "./file2.txt", "./file3.txt", };
        String[] outFiles = {"./file1.out.txt", "./file2.out.txt", "./file3.out.txt", };

        for(int i=0; i <inFiles.length; i++){
            Adder adder = new Adder(inFiles[i], outFiles[i]);
            adder.doAdd();
        }
        //Looking at this in thread terms, this does one file at a time over a single thread
    }


    public static void defaultMethod() {
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");

    }
}

