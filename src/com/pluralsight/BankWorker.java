package com.pluralsight;

public class BankWorker implements Runnable {

    private BankAccount account;
    public BankWorker(BankAccount account){this.account = account;}

    public void run(){
        for(int i=0; i<10;i++) {
            System.out.println("Im here");
            int startBalance = account.getBalance();
            account.deposit(10);
            int endBalance = account.getBalance();
            System.out.println(endBalance + startBalance);
        }
    }


}
