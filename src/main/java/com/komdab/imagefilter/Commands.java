package com.komdab.imagefilter;

import org.apache.commons.cli.*;

public class Commands {

    private static Options options;

    private static void createOptions()
    {
        options = new Options();
        Option filter = Option.builder().longOpt("filters").desc("Select filter to apply in picture").hasArg().valueSeparator(':').build();
        Option help = Option.builder().longOpt("h").desc("Return this message").build();
        Option input = Option.builder().longOpt("i").desc("Select input directory from pictures").hasArg().build();
        Option output = Option.builder().longOpt("o").desc("Select output directory from pictures").hasArg().build();
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

    public static void help(){

    }
}
