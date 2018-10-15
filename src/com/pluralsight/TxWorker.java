package com.pluralsight;

public class TxWorker implements Runnable{

    protected BankAccount account;
    protected char txType; //'w' -> withdraw, 'd' -> deposti
    protected  int amt;

    public TxWorker(BankAccount account, char txType, int amt){
        this.account = account; this.txType = txType; this.amt = amt;
    }

    public void run(){
        //This prevents ops from running over each other
        if (txType =='w'){
            account.withdraw(amt); System.out.println("Finishing op "+account.getBalance() );}
        else if (txType == 'd') {
            synchronized (account){
            account.deposit(amt);
                System.out.println("Finishing op "+account.getBalance() );
                if (account.getBalance() > 500) {
                    int bonus = (int) ((account.getBalance() - 500) * 0.1);
                    account.deposit(bonus);
                    System.out.println("Finishing op "+account.getBalance() );
                }
            }
        }
    }
}
