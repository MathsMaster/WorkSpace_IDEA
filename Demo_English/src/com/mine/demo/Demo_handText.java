package com.mine.demo;

import com.mine.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by xulp
 * on 2023/4/25
 */
public class Demo_handText {

    public static void main(String [] args)
    {
        String filePath = "/Users/xulp/Documents/文档/";
//        String fileName = "英语常用词汇_频率排序.txt";
        String fileName = "英语2000常用词汇.txt";
        List<String> list = FileUtils.readFileToList(filePath + fileName);
//        List<String> list_new = new ArrayList<>();
//        for(String s : list)
//        {
//            if(s.contains(" "))
//            {
//                String [] ss = s.split(" ");
//                list_new.add(ss[1]);
//            }
//        }
//        FileUtils.outPutToFile(list_new,filePath+"处理后的"+ fileName);
        FileUtils.otherOutPutToFile(list,filePath+"百词斩的"+ fileName);

    }
}
