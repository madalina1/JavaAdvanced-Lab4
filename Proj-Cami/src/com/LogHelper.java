package com;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(urlPatterns = {"/*"})
public class LogHelper {

    private final BufferedWriter writer;
    private File outputFile =new File("D:\\log.txt");

    public LogHelper() throws IOException {
        if(!outputFile.exists()){ //The JavaDoc says that it is not certain if the file will be created
            outputFile.createNewFile();
        }

        this.writer = new BufferedWriter(new FileWriter(outputFile, true));

        tryWriteLine(" ");
        tryWriteLine("Request:");
    }

    public void tryWriteLine(String line) {
        try {
            writeLine(line);
        } catch(IOException ioe){
            //Your exception handling here
        }
    }

    public void writeLine(String line) throws IOException {
        if(line == null){
            throw new IllegalArgumentException("line may not be null");
        }

        this.writer.write(line);
        this.writer.newLine();
        this.writer.flush(); //Make sure the line we just wrote is written and kept if the application crashes
    }


}
