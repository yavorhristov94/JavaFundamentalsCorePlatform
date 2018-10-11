package com.pluralsight;
//using non-safe account
public class NonSafeWorker implements Runnable {

    private NonSafeAccount account;
    public NonSafeWorker(NonSafeAccount account){this.account = account;}

    public void run(){
        for(int i=0; i<10;i++) {
            System.out.println("Running Method");
            int startBalance = account.getBalance();
            synchronized(account){ //locks the account
            account.deposit(10);}
            int endBalance = account.getBalance();
            System.out.println(startBalance + " " + endBalance);
        }
    }
}
