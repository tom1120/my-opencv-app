package com.mycompany.app.chapter3;



import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class AppMorphology 
{
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	public static void main(String[] args) throws Exception {
		//String filePath = "src/main/resources/images/baboon.jpg";
		String filePath = "src/main/resources/images/c.png";
		Mat newImage = Imgcodecs.imread(filePath);

		if(newImage.dataAddr()==0){
			System.out.println("Couldn't open file " + filePath);
		}else{

			GUIMorphology gui = new GUIMorphology("Morphology Example", newImage);
			gui.init();
		}
		return;
	}
}