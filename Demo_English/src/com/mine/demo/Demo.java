package com.mine.demo;

import com.mine.utils.IOUtils;
import com.mine.utils.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/*
    对真题中的单词进行匹配以及统计
 */
public class Demo {

    //最近20年的真题
    final static String inputFile_1_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2010-2021英语一真题.txt";
    final static String inputFile_2_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语二/2010-2021英语二真题.txt";
    final static String inputFile_3_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语1998-2009/1998-2009真题.txt";
    final static String outPutFile_01_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/全部真题的统计/剩下没有匹配上的部分.txt";
    final static String outPutFile_01_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/全部真题的统计/统计后匹配上的部分.txt";
    final static String outPutFile_01_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/全部真题的统计/真题集中出现过的单词的统计.txt";

    //只含有最近20年真题的阅读和翻译部分
    final static String inputFile_1_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2010-2021英语一阅读和翻译.txt";
    final static String inputFile_2_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语二/2010-2021英语二阅读和翻译.txt";
    final static String inputFile_3_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语1998-2009/1998-2009阅读和翻译.txt";
    final static String outPutFile_02_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读和翻译真题的统计/剩下没有匹配上的部分.txt";
    final static String outPutFile_02_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读和翻译真题的统计/统计后匹配上的部分.txt";
    final static String outPutFile_02_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读和翻译真题的统计/阅读和翻译中出现过的单词的统计.txt";

    //只含有最近20年真题的阅读和翻译的题目
    final static String inputFile_1_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2010-2021英语一阅读和翻译题目.txt";
    final static String inputFile_2_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语二/2010-2021英语二阅读和翻译题目.txt";
    final static String inputFile_3_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语1998-2009/1998-2009阅读和翻译的题目.txt";
    final static String outPutFile_03_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读和翻译题目的统计/剩下没有匹配上的部分.txt";
    final static String outPutFile_03_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读和翻译题目的统计/统计后匹配上的部分.txt";
    final static String outPutFile_03_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读和翻译题目的统计/阅读和翻译题目中出现过的单词的统计.txt";

    //只含有最近20年真题的阅读题目
    final static String inputFile_1_4 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2010-2021英语一阅读题目.txt";
    final static String inputFile_2_4 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语二/2010-2021英语二阅读题目.txt";
    final static String inputFile_3_4 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语1998-2009/1998-2009阅读题目.txt";
    final static String outPutFile_04_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读题目的统计/剩下没有匹配上的部分.txt";
    final static String outPutFile_04_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读题目的统计/统计后匹配上的部分.txt";
    final static String outPutFile_04_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读题目的统计/阅读题目中出现过的单词的统计.txt";

    //只含有最近20年真题的阅读部分
    final static String inputFile_1_5 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语一/2010-2021英语一阅读部分.txt";
    final static String inputFile_2_5 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语二/2010-2021英语二阅读部分.txt";
    final static String inputFile_3_5 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/英语1998-2009/1998-2009阅读部分.txt";
    final static String outPutFile_05_1 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读的统计/剩下没有匹配上的部分.txt";
    final static String outPutFile_05_2 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读的统计/统计后匹配上的部分.txt";
    final static String outPutFile_05_3 = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读的统计/阅读题目中出现过的单词的统计.txt";

    public static void main(String[] args) {

        System.out.println("统计最近20年的真题");
        run(inputFile_1_1, inputFile_2_1, inputFile_3_1, outPutFile_01_1, outPutFile_01_2, outPutFile_01_3);
        System.out.println("统计最近20年真题的阅读和翻译部分");
        run(inputFile_1_2, inputFile_2_2, inputFile_3_2, outPutFile_02_1, outPutFile_02_2, outPutFile_02_3);
        System.out.println("统计最近20年真题的阅读和翻译的题目");
        run(inputFile_1_3, inputFile_2_3, inputFile_3_3, outPutFile_03_1, outPutFile_03_2, outPutFile_03_3);
        System.out.println("统计最近20年真题的阅读题目");
        run(inputFile_1_4, inputFile_2_4, inputFile_3_4, outPutFile_04_1, outPutFile_04_2, outPutFile_04_3);
        System.out.println("统计最近20年真题的阅读部分");
        run(inputFile_1_5, inputFile_2_5, inputFile_3_5, outPutFile_05_1, outPutFile_05_2, outPutFile_05_3);
    }


    static void run(String inputPath1, String inputPath2, String inputPath3, String outPutPath1, String outPutPath2, String outPutPath3) {

        ArrayList<String> allWordList, allPaperWordList;// 分别存放从标准单词集和真题集里读取出来的单词,真题集里的单词可能存在一定的重复
        HashMap<String, Integer> resultMap;
        allWordList = new ArrayList<>();
        allPaperWordList = new ArrayList<String>();
        resultMap = new HashMap<>();

        readAllStandardWord(allWordList);// 读取出5500个单词
        readWordByTestPaper(inputPath1, allPaperWordList);// 读取试卷中的单词
        readWordByTestPaper(inputPath2, allPaperWordList);// 读取试卷中的单词
        readWordByTestPaper(inputPath3, allPaperWordList);// 读取试卷中的单词

        System.out.println("标准单词数量 : " + allWordList.size());
        System.out.println("真题单词总量(未去重) : " + allPaperWordList.size());
        statisWord(allPaperWordList, "", outPutPath3);
        // 操作数据进行匹配
        matchAllWord(allWordList, allPaperWordList, resultMap);// 进行

        // 输出结果
        outPutResult(allPaperWordList, resultMap, outPutPath1, outPutPath2, outPutPath3);
        System.out.println();
    }

    static void statisWord(ArrayList<String> list, String tag, String outPutPath3) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        // 统计真题集里不重复的单词数量
        for (String word : list) {
            map.put(word, map.get(word) == null ? 1 : map.get(word) + 1);
        }
        System.out.println(tag + "真题单词除重后数量 : " + map.keySet().size());
        IOUtils.outPutData(outPutPath3, map);
    }

    static void outPutResult(ArrayList<String> allPaperWordList, HashMap<String, Integer> resultMap, String outPutPath1, String outPutPath2, String outPutPath3) {
        System.out.println("匹配上的单词数量(去重后) : " + resultMap.keySet().size());
        IOUtils.outPutData(outPutPath2, resultMap);
        System.out.println("没有匹配上的单词数量(含重复) : " + allPaperWordList.size());
        IOUtils.outPutData(outPutPath1, allPaperWordList);
    }

    static void matchAllWord(ArrayList<String> allWordList, ArrayList<String> allPaperWordList, HashMap<String, Integer> resultMap) {

        String standardWord;// 对应着标准单词集里的单词
        String paperWord;// 对应着真题集中的单词

        // 将真题集中的单词取出来，与map中的key进行粗略的匹配
        for (int i = allPaperWordList.size() - 1; i >= 0; i--) {
            paperWord = allPaperWordList.get(i);
            for (int j = 0; j < allWordList.size(); j++)// 取出标准单词
            {
                standardWord = allWordList.get(j);
                if (compareWord(standardWord, paperWord)) {
                    if (resultMap.get(standardWord) == null)// 表示该单词还没进行存储
                    {
                        resultMap.put(standardWord, 1);
                    } else {
                        int value = resultMap.get(standardWord);
                        resultMap.put(standardWord, value + 1);
                    }
                    allPaperWordList.remove(i);// 如果匹配上了，就从真题集中删除对应的元素
                    break;// 匹配上后,就跳出内层循环，使用下个单词继续和5500个单词比较
                }
            }
        }

    }

    //
    static void readAllStandardWord(ArrayList<String> allWordList) {
        FileInputStream fis = null;
        BufferedReader br = null;// 用于读取词汇的输入流

        String info = "";
        String[] strArray;
        try {
            File fInput = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500正序.txt");

            // 读取词汇以及读取单词意思
            if (fInput.isFile() && fInput.canRead()) {
                br = new BufferedReader(new FileReader(fInput));
                while ((info = br.readLine()) != null) {

                    strArray = info.split(" ");// 拆分后的样式: 5376 whistle n.口哨,汽笛;口哨声,汽笛声 v.吹口哨;鸣笛
                    // 将单词分离出来
                    if (strArray != null && strArray.length > 1) {
                        allWordList.add(strArray[1]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (fis != null)
                    fis.close();
                if (br != null)
                    br.close();
            } catch (Exception e) {

            }

        }
    }

    static void readWordByTestPaper(String fileName, ArrayList<String> allPaperWordList) {
        FileInputStream fis = null;
        BufferedReader br = null;// 用于读取词汇的输入流

        String info = "";
        String[] strArray;
        try {
            File fInput = new File(fileName);

            // 读取词汇以及读取单词意思
            if (fInput.isFile() && fInput.canRead()) {
                br = new BufferedReader(new FileReader(fInput));
                while ((info = br.readLine()) != null) {
                    if (TextUtils.isEmpty(info) || !TextUtils.isContainEnChar(info))// 过滤空行以及没有包含英文字符的部分
                        continue;

                    strArray = info.split(" ");
//					// 将单词分离出来
                    if (strArray != null && strArray.length > 1) {
                        for (String str : strArray) {
                            // 在这里完成过滤操作(20 points) SHEET. comments. 3)​give briefly, “Whether companies”
                            // text? 40. ​[D] (OECD) tax),
                            str = TextUtils.filterString(str);
                            allPaperWordList.add(str);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (fis != null)
                    fis.close();
                if (br != null)
                    br.close();
            } catch (Exception e) {

            }

        }
    }

    static boolean compareWord(String standardWord, String matchedWord) throws NullPointerException {
        if (TextUtils.isEmpty(standardWord) || TextUtils.isEmpty(matchedWord))
            return false;

        // 传进来的单词首先进行大小写的处理
        matchedWord = matchedWord.toLowerCase();
        standardWord = standardWord.toLowerCase();

        if (standardWord.equals(matchedWord))// 进行严格的匹配
            return true;

        // ing,ed,ily,s,es, im,un
        // 如果严格的匹配不成功的话，再进行精细的匹配
        if (matchedWord.endsWith("ing") || matchedWord.endsWith("ily") || matchedWord.endsWith("ed")
                || matchedWord.endsWith("es") || matchedWord.endsWith("s") || matchedWord.startsWith("im")
                || matchedWord.startsWith("un")) {

            if (matchedWord.endsWith("ing") || matchedWord.endsWith("ily")) {
                if (matchedWord.length() <= 4)
                    return false;
                matchedWord = matchedWord.substring(0, matchedWord.length() - 4);
            } else if (matchedWord.endsWith("ed") || matchedWord.endsWith("es")) {
                if (matchedWord.length() <= 3)
                    return false;
                matchedWord = matchedWord.substring(0, matchedWord.length() - 3);
            } else if (matchedWord.endsWith("s")) {
                matchedWord = matchedWord.substring(0, matchedWord.length() - 1);
            } else if (matchedWord.startsWith("im") || matchedWord.startsWith("un")) {
                matchedWord = matchedWord.substring(2, matchedWord.length());
            }
            if (standardWord.contains(matchedWord))// 进行匹配
                return true;
        }

        return false;
    }

}
