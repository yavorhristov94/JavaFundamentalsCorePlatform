package com.pluralsight;

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

import static sun.security.krb5.internal.crypto.Nonce.value;


public class Main {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface WorkHandler{boolean useThreadPool();} //declaring an annotation
    //depending on if we wanna use a thread pool or not

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ProcessedBy{Class<?> value();}

    static Logger logger2 = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
    //New way, since it makes it a static value for all and does the whole call in one line

    static ExecutorService pool = Executors.newFixedThreadPool(5);

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
            BankAccount acct1 = new BankAccount(100, "One");


//            instancingTypes("com.pluralsight.AccountWorker", acct1);
//              workDispatch("com.pluralsight.AccountWorker2", acct1);
//            annotatedWorkDispatch("com.pluralsight.AccountWorker2", acct1);
            annotatedWorkDispatch2(acct1);

            //We want to get the simplename under the Class class for this account
            //But for this, we need to reference the class itself first
            //check methods for info on this

//           showName(getClassReference(acct1));
//
//           //But also, we can just do the literal route
//            Class<?> c = BankAccount.class;
//            showName(c);
//
//
//            typeModifiers(acct1);
//            methodInfo(acct1);
//
//            callRemoteMethod(acct1);
//            System.out.println(acct1.getBalance());
        } catch (Exception e){System.out.println(e.getMessage() + e.getCause());}
    }

    static void annotatedWorkDispatch2(Object workerTarget) throws Exception{
        Class<?> wTarget = workerTarget.getClass();
        ProcessedBy pb = wTarget.getAnnotation(ProcessedBy.class);

        Class<?> workerType = pb.value();
        //we get the class object of the annotation, stating which worker this should be
        TaskWorker worker = (TaskWorker) workerType.getDeclaredConstructor().newInstance();

        WorkHandler wh = workerType.getAnnotation(WorkHandler.class);
        if(wh == null) System.out.println("Boo");

        if (wh.useThreadPool()) pool.submit(new Runnable() {
            @Override
            public void run() {
                worker.doWork();
            }
        });
        else
            worker.doWork();

    }

    static void annotatedWorkDispatch(String workerTypeName, Object workerTarget)
            throws  Exception{
        Class<?> workerType = Class.forName(workerTypeName);
        TaskWorker worker = (TaskWorker) workerType.getDeclaredConstructor().newInstance();

        //by doing this, we limit the reflection work done, thus improving performance

        worker.setTarget(workerTarget);

        WorkHandler wh = workerType.getAnnotation(WorkHandler.class);
        if(wh == null) System.out.println("Boo");

        if (wh.useThreadPool()) pool.submit(new Runnable() {
            @Override
            public void run() {
                worker.doWork();
            }
        });
        else
        worker.doWork();
    }

    static void workDispatch(String workerTypeName, Object workerTarget)
            throws  Exception{
        Class<?> workerType = Class.forName(workerTypeName);
        TaskWorker worker = (TaskWorker) workerType.getDeclaredConstructor().newInstance();

        //by doing this, we limit the reflection work done, thus improving performance

        worker.setTarget(workerTarget);
        worker.doWork();
    }

    static void instancingTypes(String workerTypeName, Object workerTarget)
            throws  Exception{
        Class<?> workerType = Class.forName(workerTypeName);
        Class<?> targetType = workerTarget.getClass();
        //We first get references to both classes

        //Now, we need the consturctor that accepts that type (i.e. Bank account)
        Constructor c = workerType.getConstructor(targetType);

        //we instance it up in an object
        Object worker = c.newInstance(workerTarget);
        //we give the object the method and run it
        Method doWork = workerType.getMethod("doWork");
        doWork.invoke(worker);
    }

    static void callRemoteMethod (Object obj) throws Exception{
        Class<?> c = obj.getClass();
        Method m = c.getMethod("getId"); //we find the method
        Object result = m.invoke(obj); //we can then have the method run on objects
        //that normally would not know them!

        System.out.println(result);

    //for parameters, we provide each parameter's class
        Method m2 = c.getMethod("deposit", int.class);
        m2.invoke(obj, 100);
    }

    static void methodInfo(Object obj){
        Class<?> c = obj.getClass();

        Method[] methods = c.getMethods();//To note - this gets ALL INHERITED ones
        for(Method m:methods){
            if(m.getDeclaringClass() != Object.class)
                //So we must stop it at the point we want
            System.out.println(m.getName());
        }

    }

   static void typeModifiers(Object obj){
        Class<?> theClass = obj.getClass();
        int modifiers = theClass.getModifiers();

        //If the bit for Modifier is on, print it out
        if((modifiers & Modifier.FINAL) >0) System.out.println("Bitwise - final on");
        if(Modifier.isFinal(modifiers)) System.out.println("Modifier check - final on");
    }

    public static Class getClassReference(Object obj){
        //So we put in the object in question, get a reference to its Class class
        Class<?> c = obj.getClass();
        return c;
        //then send it along to the method that works with it
    }

    public static void showName (Class<?> theClass){
        System.out.println(theClass.getSimpleName());
    }

    public static void defaultMethod() {
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");

    }
}

