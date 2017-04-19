package com.mycompany.app.practice;/**
 * Created by zhaoyipc on 2017/4/19.
 */

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * @author zhaoyi
 * @date 2017-04-2017/4/19-8:52
 */
public class MatInitTest {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static void main(String[] args) {
        /**
         * 这里的type可以是任何的预定义类型，预定义类型的结构如下所示:
         CV_<bit_depth>(S|U|F)C<number_of_channels>

         1--bit_depth---
             比特数---代表8bite,16bites,32bites,64bites---举个例子吧--比如说,如
             如果你现在创建了一个存储--灰度图片的Mat对象,这个图像的大小为宽100,高100,那么,现在这张
             灰度图片中有10000个像素点，它每一个像素点在内存空间所占的空间大小是8bite,8位--所以它对
             应的就是CV_8
         2--S|U|F--
             S--代表---signed int---有符号整形
             U--代表--unsigned int--无符号整形
             F--代表--float---------单精度浮点型
         3--C<number_of_channels>----代表---一张图片的通道数,比如:
             1--灰度图片--grayImg---是--单通道图像
             2--RGB彩色图像---------是--3通道图像
             3--带Alph通道的RGB图像--是--4通道图像
         */
        System.out.println("mat = [" +Core.VERSION + "]");
        System.out.println("==========CvType.CV_8UC1==============");
        Mat mat=new Mat(1,1,CvType.CV_8UC1);
        mat.put(0,0,1);
        System.out.println( mat.dump());
        System.out.println("===========CvType.CV_8UC2=============");
        mat.empty();
        mat.create(1,1,CvType.CV_8UC2);
        mat.put(0,0,1,1);
        System.out.println( mat.dump());
        System.out.println("==========CvType.CV_8U==============");
        mat.empty();
        mat.create(1,1,CvType.CV_8U);
        //使用CV_8U以下两种赋值方式都可以
        mat.put(0,0,1);
        mat.put(0,0,1,1);
        System.out.println( mat.dump());
        //Channels count should be 1..511
        System.out.println("==========CvType.CV_8UC(1)==============");
        mat.empty();
        mat.create(1,1,CvType.CV_8UC(1));
        mat.put(0,0,1);
        System.out.println( mat.dump());
        System.out.println(mat.get(0,0)[0]);
        System.out.println("==========CvType.CV_8UC(2)==============");
        mat.empty();
        mat.create(1,1,CvType.CV_8UC(2));
        mat.put(0,0,1,2);
        System.out.println( mat.dump());
        System.out.println(mat.get(0,0)[1]);
        System.out.println("===========CvType.CV_8UC(3)=============");
        mat.empty();
        mat.create(1,1,CvType.CV_8UC(3));
        mat.put(0,0,1,2,3);
        System.out.println( mat.dump());
        System.out.println(mat.get(0,0)[2]);



    }
}
