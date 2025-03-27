package com.mine.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * created by xulp
 * on 2021/9/11
 */
public class CaculateNum {

    private static String filePathName = "/Users/xulp/Documents/MyGithub/MyFilesStroe/英语/英语阅读中不认识的单词统计/英语真题中不认识的单词.txt";
    private static final List<String> list = new ArrayList<>();


    public static void main(String [] args) {

        test();
    }

    static void test() {
        calculateWordNum();
        System.out.println("最后计算下来的单词数量是 : " + list.size());
        for(String word : list)
        {
            System.out.println(word);
        }
    }

    /*
        计算统计中的在在标准真题集中出现过的单词数量
        思路:
            将每一行读取进来后，按照字符串进行拆分，然后判断当前行的单词后面是否出现了"//"符号
     */
    static void calculateWordNum() {
        FileInputStream fos = null;
        BufferedReader br = null;
        String line;
        try {
            File fInput = new File(filePathName);

            br = new BufferedReader(new FileReader(fInput));
            while ((line = br.readLine()) != null) {
                String[] ss = line.split("\t");
                if (judgeExistIn5500(ss)) {//判断当前行里是否存在5500个单词里面的词
                    String word = ss[0].trim();
                    if (!judgeExist(word, list))//再判断下集合中是否存在对应的单词
                        if(word.startsWith("//"))
                            word = word.substring(2,word.length()-1);
                        list.add(word);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (br != null)
                    br.close();
            } catch (Exception e) {

            }
        }
    }

    /*
        判断当前行中，除了首字母外，是否存在"//"，如果存在的话，就表示当前的这个不在标准集合里
     */
    static boolean judgeExistIn5500(String[] ss) {
        if (ss == null || ss.length == 0) {
            return false;
        }

        for (int i = 1; i < ss.length; i++) {
            if (ss[i].contains("//")) {
                return false;//表示不存在于5500的标准单词集里
            }
        }
        return true;//表示存在于标准单词集里
    }


    /*
        判断集合里面是否已经存在对应的单词了
     */
    static boolean judgeExist(String word, List<String> list) {
        if (word == null)
            return false;
        for (String s : list) {
            if (s.contains(word) || word.contains(s))
                return true;
        }
        return false;
    }
}
