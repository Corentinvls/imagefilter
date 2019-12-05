package com.komdab.imagefilter;

import org.apache.commons.cli.*;
import org.ini4j.Ini;
import java.io.File;
import java.io.FileNotFoundException;
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
        Option config = Option.builder("cf").longOpt("config-file").argName("file").desc("Select config.ini or log").hasArg().build();
        options.addOption(filter);
        options.addOption(help);
        options.addOption(input);
        options.addOption(output);
        options.addOption(config);
    }

    public static CommandLine commandCreate(String[] args) throws ParseException {
        createOptions();
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public static void verifyCli(String[] args) {
        CommandLine line;
        try {
            Conf conf = new Conf("config.ini");

            logger = new Logger(conf.fileLog);
            if (conf.created) {
                logger.write("File config.ini created.");
            }
            Tools.annonce(true);
            logger.write("Command line : " + Arrays.toString(args));

            line = Commands.commandCreate(args);
            if (line.hasOption("cf")) {
                conf = new Conf(line.getOptionValue("cf"));
            }
            String input = conf.input;
            String output = conf.output;
            String[] filters = conf.filters;

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
            if (!new File(input).exists()) {
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
            if (new File(output).mkdir()) {
                String s = "New output directory " + output + " created !";
                System.out.println(s);
                logger.write(s);
            }

            String s = "Process started...";
            System.out.println(s);
            logger.write(s);
            s = "input directory : " + input;
            System.out.println(s);
            logger.write(s);
            s = "output directory : " + output;
            System.out.println(s);
            logger.write(s);
            Tools.process(input, output, filters);
            s = "Process finished !";
            System.out.println(s);
            logger.write(s);
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            logger.write(e.getMessage());
        } catch (ParseException e) {
            String s = "Command error !";
            System.out.println(s);
            logger.write(s);
            System.out.println("Command error, wrong entry");
        }
    }

    public static void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("help", options, true);
    }
}