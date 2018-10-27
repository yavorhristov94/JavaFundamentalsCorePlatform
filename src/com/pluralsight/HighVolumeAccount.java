package com.pluralsight;

public class HighVolumeAccount extends BankAccount implements Runnable {

    public HighVolumeAccount(String id) {
        super(id);
    }

    public HighVolumeAccount(int balance, String id) {
        super(balance, id);
    }

    public void token(){System.out.print("this is a token method");}
    public void run(){token();}

}
