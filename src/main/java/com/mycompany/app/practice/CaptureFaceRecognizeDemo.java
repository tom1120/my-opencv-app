package com.mycompany.app.practice;/**
 * Created by zhaoyipc on 2017/4/20.
 */

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


import java.io.*;
import java.util.Vector;

/**
 * @author zhaoyi
 * @date 2017-04-2017/4/20-9:59
 */
public class CaptureFaceRecognizeDemo {


    static Mat norm_0_255(Mat src) {
        // 创建和返回一个归一化后的图像矩阵:
        Mat dst=null;
        switch(src.channels()) {
            case 1:
                Core.normalize(src, dst, 0,255, Core.NORM_MINMAX, CvType.CV_8UC1);
            break;
            case 3:
                Core.normalize(src, dst, 0,255, Core.NORM_MINMAX, CvType.CV_8UC3);
            break;
            default:
                src.copyTo(dst);
                break;
        }
        return dst;
    }


    //使用CSV文件去读图像和标签存储在images和labels中
    /**
     *
     * @param filename 文件名
     * @param images 图像向量
     * @param labels 标签向量
     * @param separator 文本文件分割字符，默认为;
     */
    static void read_csv(final String filename, Vector<Mat> images, Vector<String> labels, String separator) {
        File file=new File(filename);


        if(!file.isFile()){
            String errormsg="No valid input file was given, please check the given filename.";
            try {
                throw new Exception(errormsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        StringBuilder result = new StringBuilder();
        try {
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String s=null;
            while ((s=br.readLine())!=null){//一行读一行
//                result.append(s);
                String[] strings=s.split(separator);
                String personImagePath=strings[0];
                String persionLabel=strings[1];
                //3以上版本
//                Mat mat= Imgcodecs.imread(filename);
                Mat mat= Imgcodecs.imread(filename);
                images.add(mat);
                labels.add(persionLabel);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private String getResourcePath(String path) {
        String absoluteFileName = getClass().getResource(path).getPath();
        absoluteFileName = absoluteFileName.replaceFirst("/", "");
        return absoluteFileName;
    }



    public static void main(String[] args) {
        // 2个容器来存放图像数据和对应的标签
        Vector<Mat> images=new Vector<Mat>();
        Vector<String> labels=new Vector<String>();
        // 读取数据. 如果文件不合法就会出错
        CaptureFaceRecognizeDemo captureFaceRecognizeDemo=new CaptureFaceRecognizeDemo();
        String fileName=captureFaceRecognizeDemo.getResourcePath("/att_faces/at.txt");
        String separator=";";
        try {
            read_csv(fileName,images,labels,separator);
        }catch (Exception e){
            System.out.println("读取文件失败！");
            e.printStackTrace();
        }

        // 得到第一张照片的高度. 在下面对图像
        // 变形到他们原始大小时需要
        int height = images.get(0).rows();
        // 下面的几行代码仅仅是从你的数据集中移除最后一张图片
        //[gm:自然这里需要根据自己的需要修改，他这里简化了很多问题]
        Mat testSample = images.get(images.size() -1);
        String testLabel = labels.get(labels.size() -1);
        images.remove(labels.size() -1);
        labels.remove(labels.size() -1);

        // 下面几行创建了一个特征脸模型用于人脸识别，
        // 通过CSV文件读取的图像和标签训练它。
        // T这里是一个完整的PCA变换
        //如果你只想保留10个主成分，使用如下代码

        //
        // 如果你还希望使用置信度阈值来初始化，使用以下语句：
        //      cv::createEigenFaceRecognizer(10, 123.0);
        //
        // 如果你使用所有特征并且使用一个阈值，使用以下语句：
        //      cv::createEigenFaceRecognizer(0, 123.0);







    }

}
