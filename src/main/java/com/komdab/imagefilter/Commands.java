package com.komdab.imagefilter;

import org.apache.commons.cli.*;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

public class Commands {

    private static Options options;

    private static void createOptions() {
        options = new Options();
        Option filter = Option.builder().longOpt("filters").argName("filter name").desc("Select filter to apply in picture").hasArg().valueSeparator(':').build();
        Option help = Option.builder().longOpt("h").desc("Return this message").build();
        Option input = Option.builder().longOpt("i").argName("directory").desc("Select input directory from pictures").hasArg().build();
        Option output = Option.builder().longOpt("o").argName("directory").desc("Select output directory from pictures").hasArg().build();
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
            line = Commands.commandCreate(args);
            Ini ini = new Ini(new File("config.ini"));

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
                    System.out.println("No input directory enter !");
                    return;
                }
            }
            if (line.hasOption("o")) {
                output = line.getOptionValue("o");
                if (output.isEmpty()) {
                    System.out.println("No output directory enter !");
                    return;
                }
            }
            Tools.process(input, output, filters);
        } catch (ParseException e) {
            System.out.println("Command error, wrong entry");
        } catch (InvalidFileFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("help", options, true);
    }
}
