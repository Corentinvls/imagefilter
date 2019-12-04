package com.komdab.imagefilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tools {

    public static void process(String inputDir, String outputDir, String[] filters) {

        for (File f : getFilesFromDirectory(inputDir)) {
            String input = inputDir;
            for (String filter : filters)
            {
                ImagePath imagePath = new ImagePath(input, outputDir, f.getName());
                String[] args = filter.split(":");
                int n = 0;
                if(args.length > 1)
                {
                    try {
                        n = Integer.parseInt(args[1]);
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid parameter. It's not an integer number !");
                        return;
                    }
                }

                try {
                    switch (args[0])
                    {
                        case "blur":
                            Filter.blur(imagePath, n);
                            break;
                        case "dilate":
                            Filter.dilate(imagePath, n);
                            break;
                        case "grayscale":
                            Filter.grayscale(imagePath);
                            break;
                    }
                }
                catch (Exception e)
                {
                     System.out.println("An exception of type " + e.getClass() + " was throw !");
                }
                input = outputDir;
            }
        }
    }

    private static List<File> getFilesFromDirectory(String directory)
    {
        List<String> extensions = new ArrayList<String>();
        List<File> files = new ArrayList<File>();
        extensions.add("png");
        extensions.add("jpg");
        extensions.add("jpeg");

        for (File f: Objects.requireNonNull(new File(directory).listFiles()))
        {
            String[] t = f.getName().split("\\.");
            if(t.length > 1)
            {
                if(extensions.indexOf(t[1]) != -1)
                {
                    System.out.println(f.getName());
                    files.add(f);
                }
            }
        }
        return files;
    }
}
