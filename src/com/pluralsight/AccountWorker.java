package com.pluralsight;

@Deprecated
public class AccountWorker implements Runnable{

    BankAccount ba;
    HighVolumeAccount hva;

    public AccountWorker(HighVolumeAccount hva) {
        this.hva = hva;
    }

    public AccountWorker(BankAccount ba) {
        this.ba = ba;
    }

    public void doWork(){
        //Since HVA can do threaded work, if there is none, we do it
        Thread t = new Thread(hva != null ? hva:this);
        t.start();
    }

    public void run(){
        System.out.print("Fuckthisshit");
    }
}
