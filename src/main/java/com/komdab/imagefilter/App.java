package com.komdab.imagefilter;

import org.apache.commons.cli.*;

public class App {
    public static void main(String[] args) {
        //--filters "blur:3|grayscale" -i "imgs" -o "output_imgs";
        Logger logger = new Logger();
        Commands.verifyCli(args);
    }


}