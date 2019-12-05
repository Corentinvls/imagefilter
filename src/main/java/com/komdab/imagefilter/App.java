package com.komdab.imagefilter;


public class App {
    public static Logger logger;
    public static void main(String[] args) {
        //--filters "blur:3|grayscale" -i "imgs" -o "output_imgs";
        logger = new Logger();
        start(true);
        Commands.verifyCli(args);
        start(false);
    }

    private static void start(boolean starting)
    {
        String s = starting ? "App imageFilter has started..." : "App imageFilter has finished...";
        System.out.println(s);
        logger.write(s);
    }
}