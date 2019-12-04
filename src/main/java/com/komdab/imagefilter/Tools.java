package com.komdab.imagefilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public  class Tools
{
    private static void process(String[] args)
    {

        File dir = new File("image");
        for (File f : Objects.requireNonNull(dir.listFiles()))
        {
            System.out.println(f.getName());
            if (!f.getName().equals(".DS_Store"))
            {
                ImagePath imagePath = new ImagePath("image", "output_image", f.getName());
                if(args[0].equals("blur") )
                {
                    System.out.println(imagePath);
                    Filter.Blur(imagePath, 3 );
                }
                else if(args[0].equals("dilate") )
                {
                    System.out.println(imagePath);
                    Filter.Dilate(imagePath, 3 );
                }
                else if(args[0].equals("grayscale"))
                {
                    System.out.println(imagePath);
                    Filter.Grayscale(imagePath);
                }
            }
        }
    }

    private List<File> getFilesFromDirectory(String directory)
    {
        List<String> extensions = new ArrayList<String>();
        List<File> files = new ArrayList<File>();
        extensions.add("png");
        extensions.add("jpg");
        extensions.add("jpeg");

        for (File f: Objects.requireNonNull(new File(directory).listFiles()))
        {
            if(extensions.indexOf(f.getName().split(".")[1]) != -1)
            {
                files.add(f);
            }
        }
        return files;
    }
}