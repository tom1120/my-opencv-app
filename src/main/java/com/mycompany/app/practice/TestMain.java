package com.mycompany.app.practice;

import org.opencv.core.Core;

public class TestMain {
  static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
  public static void main(String[] args) {
    System.out.println("Hello, OpenCV");
    new DetectFaceDemo().run();
  }
}

