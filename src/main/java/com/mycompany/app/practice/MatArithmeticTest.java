package com.mycompany.app.practice;/**
 * Created by zhaoyipc on 2017/4/19.
 */

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * @author zhaoyi
 * @date 2017-04-2017/4/19-9:51
 */
public class MatArithmeticTest {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static void main(String[] args) {
        Mat mat1=new Mat(1,1, CvType.CV_8UC1);
        mat1.put(0,0,1);
        Mat mat2=new Mat(1,1, CvType.CV_8UC1);
        mat2.put(0,0,10);
        //矩阵的加法
        Mat result=new Mat();
        Core.add(mat1,mat2,result);
        System.out.println("result = " + result.dump());
        //矩阵的减法
        Core.subtract(mat1,mat2,result);
        System.out.println("result = " + result.dump());
        Core.subtract(mat2,mat1,result);
        System.out.println("result = " + result.dump());
        //矩阵的乘法很特殊，在java中不能使用multiply方法
//        Core.multiply(mat1,mat2,result);
//        System.out.println("result = " + result.dump());

        //使用这个计算乘法，必须要转矩阵的类型
        mat1.convertTo(mat1,CvType.CV_32F);
        mat2.convertTo(mat2,CvType.CV_32F);
        Core.gemm(mat1,mat2,1,new Mat(),0,result);
        System.out.println("result = " + result.dump());

        //使用此方法是最不易出现问题的
        result=mat1.mul(mat2);
        System.out.println("result = " + result.dump());


        //矩阵的除法，与除法类似，在java中这个方法无效
//        Core.divide(mat1,mat2,result);
//        System.out.println("result = " + result.dump());

        


//        Double dot=mat1.dot(mat2);
//        System.out.println("dot = " + dot);

    }
}
