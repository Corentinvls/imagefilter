package com.komdab.imagefilter;

import org.bytedeco.opencv.presets.opencv_core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tools {
    public static void annonce(boolean starting)
    {
        String s = starting ? "App imageFilter has started..." : "App imageFilter has finished...";
        System.out.println(s);
        Commands.logger.write(s);
    }

    public static void process(String inputDir, String outputDir, String[] filters) {

        for (File f : getFilesFromDirectory(inputDir)) {
            String input = inputDir;
            for (String filter : filters) {
                ImagePath imagePath = new ImagePath(input, outputDir, f.getName());
                String[] args = filter.split(":");
                int n = 1;
                if (args.length > 1) {
                    try {
                        n = Integer.parseInt(args[1]);
                    } catch (Exception e) {
                        String s = "Invalid parameter. Can't convert " + args[1] + " to an integer number !";
                        System.out.println(s);
                        Commands.logger.write(s);
                        return;
                    }
                }

                try {
                    switch (args[0]) {
                        case "blur":
                            Filter.blur(imagePath, n);
                            break;
                        case "dilate":
                            Filter.dilate(imagePath, n);
                            break;
                        case "grayscale":
                            Filter.grayscale(imagePath);
                            break;
                        default:
                            String s = imagePath.getFileName() + " : Unknow " + args[0] + " filter !";
                            System.out.println(s);
                            Commands.logger.write(s);
                            continue;
                    }
                } catch (Exception e) {
                    String s = "An exception of type " + e.getClass() + " was throw !";
                    System.out.println(s);
                    Commands.logger.write(s);
                }
                input = outputDir;
            }
            String s = f.getName() + " process finished !";
            System.out.println(s);
            Commands.logger.write(s);
        }
    }

    private static List<File> getFilesFromDirectory(String directory) {
        List<String> extensions = new ArrayList<String>();
        List<File> files = new ArrayList<File>();
        extensions.add("png");
        extensions.add("jpg");
        extensions.add("jpeg");

        String s = "Files selected :";
        System.out.println(s);
        Commands.logger.write(s);
        for (File f : Objects.requireNonNull(new File(directory).listFiles())) {
            String[] t = f.getName().split("\\.");
            if (t.length > 1) {
                if (extensions.indexOf(t[1]) != -1) {
                    System.out.println(f.getName());
                    Commands.logger.write(f.getName());
                    files.add(f);
                }
            }
        }
        return files;
    }
}
