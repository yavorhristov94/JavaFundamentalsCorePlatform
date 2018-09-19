package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.Paths.*;

public class Helper {


    public BufferedReader openReader(String fileName) throws IOException {
        String fn = null;
        fn = fileName;

        BufferedReader reader = Files.newBufferedReader(get(fn));
        return reader;
    }

    public BufferedWriter openWriter(String fileName) throws IOException{
        String fn = null;
        fn = fileName;

        BufferedWriter writer = Files.newBufferedWriter(get(fn));
        return writer;
    }
}
