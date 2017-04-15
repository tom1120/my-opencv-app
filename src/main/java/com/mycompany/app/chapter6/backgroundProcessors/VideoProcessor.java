package com.mycompany.app.chapter6.backgroundProcessors;



import org.opencv.core.Mat;

public interface VideoProcessor {
	public Mat process(Mat inputImage);

}
