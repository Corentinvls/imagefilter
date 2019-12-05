package com.komdab.imagefilter;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.opencv.imgproc.Imgproc;

public class Filter {

    public static void blur(ImagePath imagePath, int size ) throws FilterException {
        if (size % 2 == 0) // si n pair
            throw new FilterException("Size argument must be an odd number !");
        if (size < 1)
            throw new FilterException("Size argument must be a positive number !");

        Mat image = imread(imagePath.getImagePathIn());
        if (image == null)
            throw new FilterException("Image not found !");

        GaussianBlur(image, image, new Size(size, size), 0);
        imwrite(imagePath.getImagePathOut(), image);

        String s = imagePath.getFileName() + " has been blured !";
        System.out.println(s);
        Commands.logger.write(s);
    }

    public static void dilate(ImagePath imagePath, int size) throws FilterException {

        if (size < 0)
            throw new FilterException("Size argument must be a positive number !");

        Mat image = imread(imagePath.getImagePathIn());
        if (image == null)
            throw new FilterException("Image not found !");
        Mat element = getStructuringElement(Imgproc.MORPH_RECT, new Size(2 * size + 1, 2 * size + 1));
        opencv_imgproc.dilate(image, image, element);
        imwrite(imagePath.getImagePathOut(), image);

        String s = imagePath.getFileName() + " has been dilated !";
        System.out.println(s);
        Commands.logger.write(s);
    }

    public static void grayscale(ImagePath imagePath) throws FilterException {
        Mat image = imread(imagePath.getImagePathIn());
        if (image == null)
            throw new FilterException("Image not found !");
        cvtColor(image, image, Imgproc.COLOR_RGB2GRAY);
        imwrite(imagePath.getImagePathOut(), image);

        String s = imagePath.getFileName() + " has been turn in black & white !";
        System.out.println(s);
        Commands.logger.write(s);
    }
}