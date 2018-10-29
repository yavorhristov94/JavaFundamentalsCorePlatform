package com.pluralsight;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AccountGroup implements Serializable {
    private Map<String, BankAccount> accountMap = new HashMap<>();

    private transient int totalBalance; //we can calc this
    //thus we dont need to store and retrieve that

    public void addAccount(BankAccount account){
        totalBalance+= account.getBalance();
        accountMap.put(account.getId(), account);
    }

    void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); //we tell it to de-serialize as normal
        //but then we tell it to do the math for totalBalance
        for(BankAccount account : accountMap.values()){
            totalBalance += account.getBalance();
        }
    }
}
