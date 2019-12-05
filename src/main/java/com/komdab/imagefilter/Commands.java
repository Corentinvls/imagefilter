package com.komdab.imagefilter;

import org.apache.commons.cli.*;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;

public class Commands {

    public static Logger logger;
    private static Options options;

    private static void createOptions() {
        options = new Options();
        Option filter = Option.builder("f").longOpt("filters").argName("filter name").desc("Select filter to apply in picture").hasArg().valueSeparator(':').build();
        Option help = Option.builder("h").longOpt("help").desc("Return this message").build();
        Option input = Option.builder("i").longOpt("input-dir").argName("directory").desc("Select input directory from pictures").hasArg().build();
        Option output = Option.builder("o").longOpt("output-dir").argName("directory").desc("Select output directory from pictures").hasArg().build();
        options.addOption(filter);
        options.addOption(help);
        options.addOption(input);
        options.addOption(output);
    }

    public static CommandLine commandCreate(String[] args) throws ParseException {
        createOptions();
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public static void verifyCli(String[] args) {

        CommandLine line;
        try {
            Ini ini = new Ini(new File("config.ini"));
            logger = new Logger(ini.get("config", "logFile"));
            Tools.annonce(true);
            logger.write(Arrays.toString(args));
            line = Commands.commandCreate(args);
            String[] filters = ini.get("config", "filters").split("\\|");
            String input = ini.get("config", "inputDir");
            String output = ini.get("config", "outputDir");

            if (line.hasOption("h")) {
                Commands.help();
                return;
            }
            if (line.hasOption("filters")) {
                filters = line.getOptionValue("filters").split("\\|");
            }
            if (line.hasOption("i")) {
                input = line.getOptionValue("i");
                if (input.isEmpty()) {
                    String s = "No input directory enter !";
                    System.out.println(s);
                    logger.write(s);
                    return;
                }
            }
            if(!new File(input).exists())
            {
                String s = "Directory " + input + " not found !";
                System.out.println(s);
                logger.write(s);
                return;
            }
            if (line.hasOption("o")) {
                output = line.getOptionValue("o");
                if (output.isEmpty()) {
                    String s = "No output directory enter !";
                    System.out.println(s);
                    logger.write(s);
                    return;
                }
            }
            if(new File(output).mkdir())
            {
                String s = "New output directory " + output + " created !";
                System.out.println(s);
                logger.write(s);
            }

            String s = "Process started...\ninput directory : " + input + "\noutput directory : " + output;
            System.out.println(s);
            logger.write(s);
            Tools.process(input, output, filters);
            s = "Process finished !";
            System.out.println(s);
            logger.write(s);
        } catch (ParseException e) {
            String s = "Command error !";
            System.out.println(s);
            logger.write(s);
            System.out.println("Command error, wrong entry");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("help", options, true);
    }
}
