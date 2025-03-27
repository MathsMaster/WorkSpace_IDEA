package com.mine.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created by xulp
 * on 2023/4/25
 */
public class FileUtils {

    public static List<String> readFileToList(String fileName) {
        BufferedReader br = null;
        String str;
        List<String> list = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {

            }
        }
        return list;
    }

    /*
        根据传进来的list生成对应的文件
     */
    public static void outPutToFile(List<String> list, String fileName) {

        if(new File(fileName).exists())
            new File(fileName).delete();

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(fileName)));
            for (String str : list) {
                bw.write(str + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (Exception e) {

            }
        }
    }

    public static void otherOutPutToFile(List<String> list, String fileName) {

        if(new File(fileName).exists())
            new File(fileName).delete();

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(fileName)));
            for (String str : list) {
                bw.write(str + ",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (Exception e) {

            }
        }
    }
}
