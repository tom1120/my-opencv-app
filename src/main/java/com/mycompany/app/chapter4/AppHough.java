package com.mycompany.app.chapter4;



import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class AppHough 
{
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	public static void main(String[] args) throws Exception {
		//String filePath = "src/main/resources/images/building.jpg";
		String filePath = "src/main/resources/images/masp.png";
		Mat newImage = Imgcodecs.imread(filePath, Imgcodecs.CV_LOAD_IMAGE_ANYCOLOR);

		if(newImage.dataAddr()==0){
			System.out.println("Couldn't open file " + filePath);
		}else{

			GUIHough gui = new GUIHough("Hough Example", newImage);
			gui.init();
		}
		return;
	}
}