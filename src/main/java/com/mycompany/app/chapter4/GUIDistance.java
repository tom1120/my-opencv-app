package com.mycompany.app.chapter4;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.mycompany.app.chapter2.ImageProcessor;


public class GUIDistance {
	private JLabel imageView;
	private JLabel originalImageLabel; 
	private String windowName;
	private Mat image, originalImage,grayImage;

	private final ImageProcessor imageProcessor = new ImageProcessor();



	public GUIDistance(String windowName, Mat newImage) {
		super();
		this.windowName = windowName;
		this.image = newImage;
		this.originalImage = newImage.clone();
		this.grayImage = newImage.clone();
	}

	public void init() {
		setSystemLookAndFeel();
		initGUI();
	}

	private void initGUI() {
		JFrame frame = createJFrame(windowName);

		updateView();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}




	private JFrame createJFrame(String windowName) {
		JFrame frame = new JFrame(windowName);
		frame.setLayout(new GridBagLayout());


		setupImage(frame);


		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		return frame;
	}




	private void setupImage(JFrame frame) {
		JPanel imagesPanel = new JPanel();
		imageView = new JLabel();
		imageView.setHorizontalAlignment(SwingConstants.CENTER);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;

		originalImageLabel = new JLabel();
		Image originalAWTImage = imageProcessor.toBufferedImage(originalImage);
		originalImageLabel.setIcon(new ImageIcon(originalAWTImage));
		
		imagesPanel.add(originalImageLabel);
		imagesPanel.add(imageView);

		frame.add(imagesPanel,c);
		processOperation();
	}



	protected void processOperation() {

		Imgproc.Canny(originalImage, image, 220, 255, 3, false);
		Imgproc.threshold(image, image, 100, 255, Imgproc.THRESH_BINARY_INV);
		Imgproc.distanceTransform(image, image, Imgproc.CV_DIST_L2, 3);

		image.convertTo(image,CvType.CV_8UC1);



//		System.out.println(image.get(0,0)[0]);
//		System.out.println(image.get(0,0).length);
//		System.out.println("image:"+image.size());
//		Core.multiply(image, new Scalar(20), image);

//		image.convertTo(image, CvType.CV_64F);
//		Mat sc=new Mat(image.cols(),640,0,new Scalar(new double[]{1,0,0,0}));
//		sc.convertTo(sc,CvType.CV_64F);
//		Core.gemm(image ,sc, 1, new Mat(), 0, image);
//				System.out.println(image.get(0,0)[0]);
//		image.convertTo(image,CvType.CV_8UC1);
//				System.out.println(image.get(0,0)[0]);

		image=image.mul(image,20.0);
		
		updateView();
	}

	private void setSystemLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	private void updateView() {
		Mat newMat = image;
		Image outputImage = imageProcessor.toBufferedImage(newMat);
		imageView.setIcon(new ImageIcon(outputImage));
		
		Mat originalAnnotatedMat = grayImage;
		Image originalAnnotated = imageProcessor.toBufferedImage(originalAnnotatedMat);
		originalImageLabel.setIcon(new ImageIcon(originalAnnotated));
		
	}

}
