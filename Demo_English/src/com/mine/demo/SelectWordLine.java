package com.mine.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * created by xulp
 * on 2021/11/10
 * 使用代码根据背完的单词中的标记来进行筛选，用来对单词和短语进行分级;
 * *表示最容易忘记的,位于第三轮
 * //表示单词容易忘记，位于第二轮
 * 其他的则位于第一轮
 * 思路:将标准文件和第一级的文件读入两个List<String>:list_origin,list_first中,根据第一轮文件中的标记来进行判断:
 * 根据对应的索引，分别将*标记的原始行取出来，装入list_third中，将有//标记的原始行装入list_second中
 * 再根据list_second和list_third挨个的输出到对应文件"根据词根词缀进行记忆_第二轮","根据词根词缀进行记忆_第三轮"
 */
public class SelectWordLine {

    static List<String> list_origin, list_first, list_second, list_third;
    final static String fileName_origin = "/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500正序.txt";
    final static String fileName_first = "/Users/xulp/Documents/MyGithub/MyFilesStroe/英语/根据词根词缀进行记忆";
    final static String fileName_second = "/Users/xulp/Documents/MyGithub/MyFilesStroe/英语/根据词根词缀进行记忆_第二轮.txt";
    final static String fileName_third = "/Users/xulp/Documents/MyGithub/MyFilesStroe/英语/根据词根词缀进行记忆_第三轮.txt";

    public static void main(String[] args) {
        list_origin = readFileToList(fileName_origin);
        list_first = readFileToList(fileName_first);
        list_second = generateSecondListByFlag(list_origin, list_first);
        list_third = generateThirdListByFlag(list_origin, list_first);
        outPutToFile(list_second, fileName_second);
        outPutToFile(list_third, fileName_third);
    }

    /*
        根据传进来的原始文件和第一轮文件中的标记，来生成第二轮文件的集合
     */
    static List<String> generateSecondListByFlag(List<String> list_origin, List<String> list_first) {
        List<String> list = new ArrayList<>();
        if (list_first.size() == 0 || list_origin.size() == 0)
            return list;
        for (int i = 0; i < list_first.size(); i++) {
            if (list_first.get(i).contains("//"))
                list.add(list_origin.get(i));
        }
        return list;
    }

    /*
        根据传进来的原始文件和第一轮文件中的标记，来生成第三轮文件的集合
     */
    static List<String> generateThirdListByFlag(List<String> list_origin, List<String> list_first) {
        List<String> list = new ArrayList<>();
        if (list_first.size() == 0 || list_origin.size() == 0)
            return list;
        for (int i = 0; i < list_first.size(); i++) {
            if (list_first.get(i).contains("*"))
                list.add(list_origin.get(i));
        }
        return list;
    }

    /*
        将对应的文件中的单词行读入内存
     */
    static List<String> readFileToList(String fileName) {
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
    static void outPutToFile(List<String> list, String fileName) {
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
}
