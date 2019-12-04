package com.komdab.imagefilter;

import java.io.File;
import java.util.Objects;

public  class Tools {


    public static void process(String[] args) {
        File dir = new File("image");
        for (File f : Objects.requireNonNull(dir.listFiles())) {
            System.out.println(f.getName());
            if (!f.getName().equals(".DS_Store")) {
                ImagePath imagePath = new ImagePath("image", "output_image", f.getName());
                if(args[0].equals("blur") ){
                    System.out.println(imagePath);
                    Filter.Blur(imagePath, 3 );
                }
                else if(args[0].equals("dilate") ){
                    System.out.println(imagePath);
                    Filter.Dilate(imagePath, 3 );
                }
                else if(args[0].equals("grayscale") ){
                    System.out.println(imagePath);
                    Filter.Grayscale(imagePath);
                }
                
            }
        }
    }
}
