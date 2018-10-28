package com.pluralsight;

@Main.WorkHandler(useThreadPool = false) //using the declared annotation
public class AccountWorker2 implements Runnable, TaskWorker {

    BankAccount ba;

    public void setTarget(Object target){
        //First we check if we have a proper target
        //either Bank account or its extenders
        if(BankAccount.class.isInstance((target)))
            ba = (BankAccount) target;
        else
           System.out.println("Wrong object");
        //normally here we would throw an exception, but I cant be arsed
    }

    public void doWork(){
    Thread t = new Thread(
            HighVolumeAccount.class.isInstance(ba) ?(HighVolumeAccount)ba : this
    ); //If it;s an instance of HVA, we will cast the thread with it,
        //since it knows how to do runnable
        //otherwise, we will cast ourselves
        t.start();
    }

    public void run(){System.out.println("Success");}
}
