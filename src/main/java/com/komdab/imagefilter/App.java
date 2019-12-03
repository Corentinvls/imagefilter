package com.komdab.imagefilter;

import java.io.File;
import java.util.ArrayList;


public class App {
    public static void main(String[] args) {

        ArrayList<ImagePath> imagePathMap;


        File dir = new File("image");
        for(File f : dir.listFiles()){
            System.out.println(f.getName());
            if(!f.getName().equals(".DS_Store")){
                Filter filter = new Filter("image", "output_image", f.getName());
                filter.smooth();
            }

        }

    }
}
