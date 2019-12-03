package com.komdab.imagefilter;

import org.bytedeco.opencv.opencv_core.*;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;


public class Filter
{
    private String pathIn;
    private String pathOut;
    private String filename;

    public Filter(String pathIn, String pathOut, String filename)
    {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
        this.filename = filename;

    }

    public void smooth()
    {
        Mat image = imread(this.pathIn+ "/" + this.filename);
        if (image != null) {
            GaussianBlur(image, image, new Size(3, 3), 0);
            imwrite(this.pathOut + "/" + this.filename, image);
        }
    }

}
