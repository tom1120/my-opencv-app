package com.mycompany.app.practice;/**
 * Created by zhaoyipc on 2017/4/20.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhaoyi
 * @date 2017-04-2017/4/20-16:11
 */
public class AttFacesProcess {
    /**
     * 获取指定文件夹下的所有特征文件，在路径后标识出是否为同一个人编号[打标签]
     * path;number
     * @param file 指定文件
     * @param specialValue 特征值
     * @param writeFile 写入文件路径
     */
    public void readPathFile(File  file,String specialValue,String writeFile){
        OutputStream outputStream=null;
        try {
            File fileWrite=new File(writeFile);
            fileWrite.delete();

            if(file.isDirectory()){
                File[] files=file.listFiles();
                for(File subFile:files){
                    if (subFile.isDirectory()){
                        readPathFile(subFile,specialValue,writeFile);
                    }else if(subFile.getName().contains(specialValue)){

                        String filePath=subFile.getPath();
                        Pattern p=Pattern.compile("s[0-9]{1,2}");
                        Matcher m=p.matcher(filePath);
                        String s="";
                        while (m.find()){
                            s=m.group();
                            System.out.println("filePath = " + filePath);
                            System.out.println("s = " + s.replaceAll("s",""));
                            s=s.replaceAll("s","");
                        }



                        fileWrite.createNewFile();

                        outputStream= new FileOutputStream(fileWrite,true);
                        String s1= new String(filePath+";"+(Integer.valueOf(s)-1)+"\n");
                        outputStream.write(s1.getBytes());
                        outputStream.flush();
                    }else{
                        System.out.println("subFile.getName() = " + subFile.getName());
                    }
                }
            }else{
                throw new Exception("传递参数filePath要为目录!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(outputStream!=null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private String getResourcePath(String path) {
        String absoluteFileName = getClass().getResource(path).getPath();
        absoluteFileName = absoluteFileName.replaceFirst("/", "");
        return absoluteFileName;
    }

    public static void main(String[] args) {
        AttFacesProcess a=new AttFacesProcess();
        String p=a.getResourcePath("/att_faces");

        File f=new File(p);
        try {
            a.readPathFile(f,".pgm",p+"/at.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
