package com.komdab.imagefilter;



import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.opencv.imgproc.Imgproc;




public class Filter
{

    public static void Blur(ImagePath imagePath, int paramFilter)
    {
        Mat image = imread(imagePath.getImagePathIn());
        if (image != null)
        {
            GaussianBlur(image, image, new Size(paramFilter, paramFilter), 0);
            imwrite(imagePath.getImagePathOut(), image);
        }
    }

    public static void Dilate(ImagePath imagePath, int paramFilter)
    {

        Mat image = imread(imagePath.getImagePathIn());
        if (image != null)
        {
            Mat element = getStructuringElement(Imgproc.MORPH_RECT, new Size(2 * paramFilter + 1, 2 * paramFilter + 1));
            dilate(image, image, element);
            imwrite(imagePath.getImagePathOut(), image);
        }
    }



    public static void Grayscale(ImagePath imagePath)
    {
        Mat image = imread(imagePath.getImagePathIn());
        if (image != null)
        {
            cvtColor(image, image, Imgproc.COLOR_RGB2GRAY);
            imwrite(imagePath.getImagePathOut(), image);
        }
    }
}
