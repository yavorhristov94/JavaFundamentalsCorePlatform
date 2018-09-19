package com.pluralsight;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {
	// This is the base branch, from which we expand for specific lessons
    //Do NOT initialize values in the Main method unless absolutely necessary
    //Create a method clearly stating the function in its name, in case merging is needed
        //List of branches:
        /*
        master
        TryWithResources
        StringPractice


         */

        defaultMethod();
    }

    public static void defaultMethod(){
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");
    }






        public static void stringPractice() {
            // write your code here

            StringJoiner sj = new StringJoiner(", ");
            sj.add("alpha");
            sj.add("theta");
            sj.add("gamma");
            String theResult = sj.toString();

            System.out.println(theResult);


            StringJoiner sjTwo = new StringJoiner(", ");
            sjTwo.add("alpha").add("theta").add("gamma");
            theResult = sjTwo.toString();

            System.out.println(theResult);


            StringJoiner sjThree = new StringJoiner(", ", "{","}" );
            sjThree.add("alpha").add("theta").add("gamma");
            theResult = sjThree.toString();

            System.out.println(theResult);


            //Empty case handling (only if add method was never called)
            StringJoiner sjFour = new StringJoiner(", ", "{","}" );
            //  sjThree.add("alpha").add("theta").add("gamma");
            sjFour.setEmptyValue("EMPTY");
            theResult = sjFour.toString();
            System.out.println(theResult);

            sjFour.add(""); theResult = sjFour.toString();
            System.out.println(theResult);


            int david=13, dawson=11, dillon=4, gordon=2;
            String s2 = String.format("My nephews are %d, %d, %d and %d",
                    david, dawson, dillon, gordon);
            System.out.println(s2);

            matchAndPrint();
        }

        static void doWrite(int david, int dawson, int dillon, int gordon) {
            //We create a writer, so we have a stream going out
            try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("file1.txt"));)
            //Then, inside the stream, we use the formatter class, since it can be used in anything that uses the Append interface
            {
                try(Formatter f = new Formatter(writer)){
                    f.format("My nephews are %d, %d, %d and %d",
                            david, dawson, dillon, gordon);
                }
            }catch(Exception e){}
        }

        static void matchAndPrint() {
            String s1 = "apple, apple and orange please";

            //first we prime the Pattern class to tell it what to find
            Pattern pattern = Pattern.compile("\\w+");
            //Then we arm it into the matcher class that can find it
            Matcher matcher = pattern.matcher(s1);

            while(matcher.find())
                System.out.println(matcher.group());

        }
    }


