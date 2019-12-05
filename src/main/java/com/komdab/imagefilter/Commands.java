package com.komdab.imagefilter;

import org.apache.commons.cli.*;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;

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
        App.logger.write(Arrays.toString(args));
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
                    String s = "No input directory enter !";
                    System.out.println(s);
                    App.logger.write(s);
                    return;
                }
            }
            if (line.hasOption("o")) {
                output = line.getOptionValue("o");
                if (output.isEmpty()) {
                    String s = "No output directory enter !";
                    System.out.println(s);
                    App.logger.write(s);
                    return;
                }
            }
            String s = "Process started...";
            System.out.println(s);
            App.logger.write(s);
            Tools.process(input, output, filters);
            s = "Process finished !";
            System.out.println(s);
            App.logger.write(s);
        } catch (ParseException e) {
            String s = "Command error !";
            System.out.println(s);
            App.logger.write(s);
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
