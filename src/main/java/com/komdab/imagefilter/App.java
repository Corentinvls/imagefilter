package com.komdab.imagefilter;

import org.apache.commons.cli.*;

public class App {
    public static void main(String[] args)
    {
        //--filters "blur:3|grayscale" -i "imgs" -o "output_imgs"
        CommandLine line;
        try{
            line = Commands.commandCreate(args);

            String[] filters = null;
            String input = "";
            String output = "";
            if(line.hasOption("h"))
            {
                Commands.help();
                return;
            }
            if(line.hasOption("filters"))
            {
                filters = line.getOptionValue("filters").split("\\|");
            }
            if(line.hasOption("i"))
            {
                input = line.getOptionValue("i");
                if(input.isEmpty())
                {
                    System.out.println("No input directory enter !");
                    return;
                }
            }
            if(line.hasOption("o"))
            {
                output = line.getOptionValue("o");
                if(output.isEmpty())
                {
                    System.out.println("No output directory enter !");
                    return;
                }
            }
            Tools.process(input, output, filters);
        }
        catch (ParseException e)
        {
            System.out.println("Command error, wrong entry");
        }
    }
}