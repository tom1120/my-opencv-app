package com.mycompany.app.chapter6;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import com.mycompany.app.chapter2.ImageProcessor;
import com.mycompany.app.chapter6.backgroundProcessors.MixtureOfGaussianBackground;
import com.mycompany.app.chapter6.backgroundProcessors.VideoProcessor;


public class AppBackground 
{
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
	}
	
	private JFrame frame;
	private JLabel imageLabel;
	
	private JFrame playbackFrame;
	private JLabel playbackLabel;
	
	private Mat backgroundImage = new Mat();
	private Mat currentImage = new Mat();
	private Mat foregroundImage = new Mat();
	
	
	
	public static void main(String[] args) throws InterruptedException {
		AppBackground app = new AppBackground();
		app.initGUI();
		app.runMainLoop(args);
	}
	
	private void initGUI() {
		frame = new JFrame("Background Removal Example");  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setSize(400,400);  
		imageLabel = new JLabel();
		frame.add(imageLabel);
		frame.setVisible(true);
		
		playbackFrame = new JFrame("Video Playback Example");  
		playbackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		playbackFrame.setSize(400,400);  
		playbackLabel = new JLabel();
		playbackFrame.add(playbackLabel);
		playbackFrame.setVisible(true);
		
		
		
	}

	private void runMainLoop(String[] args) throws InterruptedException {
		ImageProcessor imageProcessor = new ImageProcessor();
		Image tempImage;  
		
		VideoProcessor videoProcessor;
		VideoCapture capture = new VideoCapture();
		capture.open("src/main/resources/videos/tree.avi");

		if( capture.isOpened()){
			
			capture.read(backgroundImage);
			
			//videoProcessor = new AbsDifferenceBackground(backgroundImage);
			//videoProcessor = new RunningAverageBackground();
			videoProcessor = new MixtureOfGaussianBackground();
			
			
			while (true){  
				capture.read(currentImage);  
				if( !currentImage.empty() ){
					
					
					
					foregroundImage = videoProcessor.process(currentImage);
					
					tempImage= imageProcessor.toBufferedImage(foregroundImage);
					ImageIcon imageIcon = new ImageIcon(tempImage, "Video playback");
					imageLabel.setIcon(imageIcon);
					frame.pack();  //this will resize the window to fit the image
					
					Image playbackImage = imageProcessor.toBufferedImage(currentImage);
					ImageIcon playbackImageIcon = new ImageIcon(playbackImage, "Video playback");
					playbackLabel.setIcon(playbackImageIcon);
					playbackFrame.pack();
					
					Thread.sleep(70);
				}  
				else{  
					capture = new VideoCapture("src/main/resources/videos/tree.avi");
					
					System.out.println("Looping video"); 
					//break;  
				}
			}  
		}
		else{
			System.out.println("Couldn't open video file.");
		}
		
	}
}
