package com.komdab.imagefilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logger
{
    private File file;
    private String nameFile;

    public  Logger()
    {
        nameFile = "log.log";
        file = new File(nameFile);
        try
        {
            if (file.createNewFile())
            {
                String s = "File " + file.getName() + " created. path : " + file.getAbsolutePath();
                System.out.println(s);
                FileWriter fileWriter = new FileWriter(file.getName());
                fileWriter.write(new Timestamp(System.currentTimeMillis()) + "\t: " + s + "\n");
                fileWriter.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("File " + nameFile + " can't be created !");
        }
        finally
        {
            System.out.println("\n");
        }
    }

    public void write(String message)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try
        {
            FileWriter fileWriter = new FileWriter(file.getName(), true);
            fileWriter.write(timestamp + "\t: " + message + "\n");
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("An error occurred to write log !");
            System.out.println("File path : " + file.getAbsolutePath());
            System.out.println("Message :\n" + timestamp + " : " + message);
        }
    }
}