package com.mycompany.app;



import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import com.mycompany.app.chapter2.ImageViewer;

public class TestCapture {
	
	
	static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	//If you want to manipulate the pixels on the Java side, perform the following steps:
	public void filter(Mat image){
		//1. Allocate memory with the same size as the matrix in a byte array.
		int totalBytes = (int)(image.total() * image.elemSize());
		byte buffer[] = new byte[totalBytes];
		//2. Put the image contents into that array (optional).
		image.get(0, 0,buffer);
		//3. Manipulate the byte array contents.
		for(int i=0;i<totalBytes;i++){
			if(i%3==0) buffer[i]=0;
		}
		//4. Make a single put call, copying the whole byte array to the matrix.
		image.put(0, 0, buffer);
	}
	
	public Mat openFile(String fileName) throws Exception{
		Mat newImage = Imgcodecs.imread(fileName);
		if(newImage.dataAddr()==0){
			throw new Exception ("Couldn't open file "+fileName);
		}
		return newImage;
	}
	
	
	public void captureTest() throws Exception {
		VideoCapture capture = new VideoCapture(0);
		if(!capture.isOpened()){
			throw new Exception("not get a camera!");
		}
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH,640);
		capture.set(Videoio.CAP_PROP_FRAME_HEIGHT,480);
		
	}
	
	
	public static void main(String[] args) {
		/**
		 * Base Matrix manipulation
		 */
//		Mat image2=new Mat(480,640, CvType.CV_8UC3);
//		Mat image3 = new Mat(new Size(640,480), CvType.CV_8UC3);
//		System.out.println(image2 + "rows " + image2.rows() + " cols " +
//				image2.cols() + " elementsize " + image2.elemSize());
//		
//		Mat image = new Mat(new Size(3,3), CvType.CV_8UC3, new Scalar(new
//				double[]{128,3,4}));
//		
//		System.out.println(image.dump());
		/**
		 * Pixel manipulation
		 */
//		System.out.println("=====================");
		/**
		 * Note that in the array of bytes{1,2,3},for our matrix 
		 * 1��stands for the blue channel
		 * 2��stands for the green channel
		 * 3��stands for the red channel
		 * as OpenCV stores its matrix internally in the BGR(blue,green,red) format
		 * 
		 * rows->height��cols->width
		 */
//		for(int i=0;i<image.rows();i++){
//			for(int j=0;j<image.cols();j++){
//			image.put(i, j, new byte[]{1,2,3});
//			}
//		}
		
		
		/**
		 * display an image with swing
		 */
		TestCapture tc=new TestCapture();
		ImageViewer iv=new ImageViewer();
		
		try {
			iv.show(tc.openFile("src/main/resources/images/welcome.jpg"),"Loaded image");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
