package com.komdab.imagefilter;

import org.apache.commons.cli.*;

public class App {
    public static void main(String[] args) throws ParseException {

        Options options = new Options();
        Option filter = Option.builder().longOpt("filter").hasArg().valueSeparator(':').build();
        options.addOption(filter);
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        String[] filters = line.getOptionValue("filter").split("\\|");
        Tools.process("image", "output_image", filters);
    }
}