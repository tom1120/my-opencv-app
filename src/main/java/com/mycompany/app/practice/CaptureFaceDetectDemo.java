package com.mycompany.app.practice;/**
 * Created by zhaoyipc on 2017/4/19.
 */

import com.mycompany.app.chapter2.ImageProcessor;
import com.mycompany.app.chapter5.AppCascade;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;

/**
 * @author zhaoyi
 * @date 2017-04-2017/4/19-13:57
 */
public class CaptureFaceDetectDemo {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private JFrame frame;
    private JLabel imageLabel;
    private CascadeClassifier faceDetector;

    public static void main(String[] args) {
        CaptureFaceDetectDemo app = new CaptureFaceDetectDemo();
        app.initGUI();

        app.loadCascade();

        app.runMainLoop(args);



    }
    //The following code shows how to load a trained cascade
    private void loadCascade() {
        //使用人脸识别库，但是只是支持正脸库
		String cascadePath = "src/main/resources/practiceimages/lbpcascade_frontalface.xml";
        faceDetector = new CascadeClassifier(cascadePath);
    }

    private void initGUI() {
        frame = new JFrame("Camera Input Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        imageLabel = new JLabel();
        frame.add(imageLabel);
        frame.setVisible(true);
    }

    private void runMainLoop(String[] args) {
        ImageProcessor imageProcessor = new ImageProcessor();
        Mat webcamMatImage = new Mat();
        Image tempImage;
        VideoCapture capture = new VideoCapture();
        capture.open(0);
        capture.set(Videoio.CV_CAP_PROP_FRAME_WIDTH,640);
        capture.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT,480);

        if( capture.isOpened()){
            while (true){
                capture.read(webcamMatImage);
                if( !webcamMatImage.empty() ){
                    detectAndDrawFace(webcamMatImage);
                    tempImage= imageProcessor.toBufferedImage(webcamMatImage);

                    ImageIcon imageIcon = new ImageIcon(tempImage, "Captured video");
                    imageLabel.setIcon(imageIcon);
                    frame.pack();  //this will resize the window to fit the image
                }
                else{
                    System.out.println(" -- Frame not captured -- Break!");
                    break;
                }
            }
        }
        else{
            System.out.println("Couldn't open capture.");
        }

    }

    private void detectAndDrawFace(Mat image) {
        MatOfRect faceDetections = new MatOfRect();
        //Mat image 检测图像, MatOfRect objects 检测结果集 , double scaleFactor, int minNeighbors, int flags, Size minSize 最小检测大小, Size maxSize 最大检测大小
	    faceDetector.detectMultiScale(	image, faceDetections, 1.2, 2,0,new Size(100,40),new Size());

        // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new org.opencv.core.Point(rect.x, rect.y), new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
    }
}
