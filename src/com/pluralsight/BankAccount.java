package com.pluralsight;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BankAccount implements Serializable {

    private static final long serialVersionUID = 8508191186668617676L;
    private String id;
    private int balance = 0;
    private char lastTxType;



    private int lastTxAmount;

    public BankAccount(String id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public BankAccount(String id) {
        this.id = id;
    }



    public synchronized void depostit(int amt){balance+=amt; System.out.println("Balance after deposit is "+balance);
    lastTxAmount = amt; lastTxType = 'd';}

    public synchronized void withdraw(int amt){balance-=amt; System.out.println("Balance after withdraw is "+balance);
        lastTxAmount = amt; lastTxType = 'w';}

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        //usually this is not needed, this is only for demonstration purposes
    }

     private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
        ObjectInputStream.GetField fields = in.readFields();
        id = (String) fields.get("id", null);
        balance = fields.get("balance", 0);
        lastTxType = fields.get("lastTxType", 'u');
        lastTxAmount = fields.get("lastTxAmount", -1);
    }

    public int getBalance() {
        return balance;
    }
    public String getId() {
        return id;
    }
    public int getLastTxAmount() {
        return lastTxAmount;
    }
    public char getLastTxType() {
        return lastTxType;
    }
}
