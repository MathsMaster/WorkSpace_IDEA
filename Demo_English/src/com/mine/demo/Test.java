package com.mine.demo;

import com.mine.bean.WordObj;
import com.mine.utils.IOUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * created by xulp
 * on 2021/7/18
 * <p>
 * 对传进来的词汇之间建立相互间的关系（按照意思来建立关系）
 * 思路：
 * 1，将5500个单词全部读入内存
 * 2，将3400个单词读入进内存，再把每个单独的单词取出来；
 * 3，使用3400个单词作为标志来对5500个单词进行匹配删除，将剩下的部分保留下来
 * 4，使用原有的单词的意思关联算法，对筛选后的3400个单词进行意思之间的相互关联
 */
public class Test {

    final static String inputFileName = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读的统计/统计后匹配上的部分.txt";
    final static String outPutFileName = "/Users/xulp/Documents/学习资料/考研英语/考研英语真题/阅读的统计/匹配上的单词之间的相互关联.txt";

    private static WordObj[] wordArray;
    private static List<String>[] meansArray;// 数组里放的全是List集合，集合里放的又都是String
    final static int WordMAXNum = 6000;

    public static void main(String[] args) {

        run();
    }


    static void run() {
        init();
        IOUtils.readAllWordsAndAllMeansByFile(wordArray, meansArray);// 读取出5500个单词
        List<String> list = IOUtils.read3400wordByFile(inputFileName);//将3400个单词读入内存
        screenWords(list);//使用读取出来的3400个单词对5500个单词及其意思进行过滤
        match(wordArray, meansArray);// 读取单词，对过滤后剩下的单词及其意思进行关联
        printAllWord(outPutFileName, wordArray);// 把有关联的单词都打印到一起，必然会出现很多重复的单词
    }

    static void init() {
        wordArray = new WordObj[WordMAXNum];
        meansArray = new LinkedList[WordMAXNum];
    }

    /*
        使用3400个单词，对5500个单词进行筛选
     */
    static void screenWords(List<String> list) {
        if (list == null || list.size() == 0)
            return;
        WordObj[] tempWordArray = new WordObj[WordMAXNum];
        List<String>[] tempMeansArray = new LinkedList[WordMAXNum];

        for (String word : list) {
            for (int j = 0; j < wordArray.length; j++) {
                if (word == null || wordArray[j] == null)
                    continue;
                if (word.equals(wordArray[j].getWord())) {
                    tempWordArray[j] = wordArray[j];
                    tempMeansArray[j] = meansArray[j];
                    break;
                }
            }
        }
        wordArray = tempWordArray;
        meansArray = tempMeansArray;
    }

    /*
        wordArray 存储着3400多个单词，以及对应的每个单词与之有相关意思的索引
        meansArray 存储这每个单词的多个意思，共有3400多个单词
     */
    static void match(WordObj[] wordArray, List<String>[] meansArray) {
        for (int index = 0; index < meansArray.length; index++) {
            if (meansArray[index] != null && wordArray[index] != null) {
                List<String> list = meansArray[index];//取出当前单词的多个意思
                for (String mean : list)// 挨个取出当前单词的所有意思，和其他词组的所有意思一个个的进行匹配
                {
                    // 循环取出其他的词的各个意思
                    for (int i = index + 1; i < meansArray.length; i++) {//每一次匹配时，都只用后面未经匹配过的词
                        if (meansArray[i] != null && wordArray[i] != null) {
                            List<String> otherList = meansArray[i];
                            for (String otherMean : otherList) {
                                if (otherMean == null || otherMean.isEmpty())
                                    continue;
                                if (mean.equals(otherMean)) {
                                    // 能匹配上，就开始记录
                                    List<Integer> ls = wordArray[index].getList();
                                    List<Integer> ols = wordArray[i].getList();
                                    if ((!ls.contains(i)) || (!ols.contains(index))) {
                                        ls.add(i);
                                        ols.add(index);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static List<String> readAllColum(String standardFileName) {
        List<String> list = new LinkedList<String>();// 用来存放当前这一行的所有意思
        FileInputStream fis = null;
        BufferedReader br = null;// 用于读取词汇的输入流

        String info = "";
        try {
            File fInput = new File(standardFileName);

            // 读取词汇以及读取单词意思
            if (fInput.isFile() && fInput.canRead()) {
                br = new BufferedReader(new FileReader(fInput));
                while ((info = br.readLine()) != null) {
                    list.add(info);
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
        return list;
    }

    static void printAllWord(String outPutFileName, WordObj[] wordArray) {
        //读取出来5500个单词意思
        List<String> allStrList = readAllColum("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500正序.txt");// 先读取出所有的行来

        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File fOut = new File(outPutFileName);

            if (!fOut.exists()) {
                fOut.createNewFile();
            } else {
                fOut.delete();
                fOut.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(fOut));

            for (int i = 0; i < wordArray.length; i++) {
                // 先打印自己这一行
                if (allStrList.size() > i && wordArray[i] != null)
                    bw.write(allStrList.get(i) + "\n");
                bw.flush();
                //再打印与之有相关联意思的行
                if (wordArray[i] != null && wordArray[i].getList() != null) {
                    List<Integer> list = wordArray[i].getList();
                    Integer index = 0;
                    for (int j = 0; j < list.size(); j++) {
                        index = list.get(j);
                        if (allStrList.size() > index)
                            bw.write(allStrList.get(index) + "\n");
                        bw.flush();
                    }
                    bw.write("\n");
                    bw.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (fos != null)
                    fos.close();
                if (bw != null)
                    bw.close();
            } catch (Exception e) {

            }
        }
    }

}
