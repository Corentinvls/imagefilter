package com.komdab.imagefilter;

import org.bytedeco.opencv.opencv_core.*;

import java.io.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;


public class Filter
{
    private String pathIn;
    private String pathOut;

    public Filter(String pathIn, String pathOut) throws FilterException
    {
        this.pathIn = pathIn;
        this.pathOut = pathOut;

        // create the source directory
        File f = new File(pathIn);
        if(!f.exists())
        {
            throw new FilterException("Directory '" + pathIn + "' not found !");
        }

        // create the target directory
        f = new File(pathOut);
        try
        {
            if (f.createNewFile())
            {
                printn("Directory '" + f.getName() + "' created.");
            }
        }
        catch (IOException e)
        {
            printn("Can't create Directory '" + pathOut + "' !");
        }
    }

    private void printn(Object o)
    {
        System.out.println(o);
    }

    public void smooth(String filename)
    {
        Mat imageSrc = imread(this.pathIn  + "/" + filename);
        Mat imageDst = imread(this.pathOut + "/" + filename);
        if (imageSrc != null)
        {
            GaussianBlur(imageSrc, imageDst, new Size(3, 3), 0);
            imwrite(this.pathOut + "/" + filename, imageDst);
        }
    }

}
