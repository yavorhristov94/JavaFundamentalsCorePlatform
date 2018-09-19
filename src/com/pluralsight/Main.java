package com.pluralsight;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        CreateAndPopulateZip

         */

        defaultMethod();
    }

    public static void defaultMethod(){
        System.out.println("Default method working.");
        System.out.println("You may proceed with your work.");
        System.out.println("Enjoy!");
    }
    public static void starter() {
        String [] data = {
                "line 1",
                "line 2 2",
                "line 3 3 3",
                "line 4 4 4 4",
                "line 5 5 5 5 5",
        };
        FileSystem zipFS;
        try (FileSystem zipFs = openZip(Paths.get("myData.zip"))){
            copyToZip(zipFs);
            writeToFileZipInZip1(zipFs,data);
            writeToFileZipInZip2(zipFs,data);
        }catch (Exception e){System.out.println(e.getClass().getSimpleName() + e.getMessage());}
    }

    private static FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {
        Map<String, String> providerProps = new HashMap<>(); //needed for creating the provider properties, via a key-value pair
        providerProps.put("create", "true"); //Meaning, if it doesnt already exist, create it

        URI zipURI = new URI("jar:file", zipPath.toUri().getPath(), null);
        FileSystem zipFS = FileSystems.newFileSystem(zipURI, providerProps);

        return zipFS;
    }

    private static void copyToZip(FileSystem zipFs) throws IOException{
        Path sourceFile = Paths.get("file1.txt");
//        Below is an example of how we specify from which FS we get the path.
//        Path sourceFile = FileSystems.getDefault().getPath("file.txt");
        Path destFile = zipFs.getPath("/file1copied.txt"); //can name it whatever we want

        Files.copy(sourceFile,destFile, StandardCopyOption.REPLACE_EXISTING);
    }

    //Doing the writing the old, deprecated method, through a BufferedWriter
    private static void writeToFileZipInZip1(FileSystem zipFS, String[] data) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(zipFS.getPath("newFile1.txt"))){
            for (String d:data){
                writer.write(d);
                writer.newLine();
            }
        }
    }

    //Faster method, writing using the Files class
    private static void writeToFileZipInZip2(FileSystem zipFS, String[] data) throws IOException{
        //Notice the lack of a try block - it automatically passes it up, you just need to throw it
        Files.write(zipFS.getPath("/newFile2.txt"), Arrays.asList(data),
                //This method needs to implement Itterable interface.
                //Thus, using the Arrays asList method, implements it
                Charset.defaultCharset(), StandardOpenOption.CREATE);
    }

}



