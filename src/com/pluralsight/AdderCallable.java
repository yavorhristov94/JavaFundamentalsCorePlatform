package com.pluralsight;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

public class AdderCallable implements Callable<Integer> {

    private String inFile;
    public AdderCallable(String inFile) {}


    public int returnAdd() throws IOException {
        int total = 0;
        String line = null;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))){
            while((line=reader.readLine()) !=null)
                total += Integer.parseInt(line);
        }
        return total;
    }

    public Integer call() throws IOException{
        return returnAdd();
    }//this way it returns the result of the add and the exceptions

}
