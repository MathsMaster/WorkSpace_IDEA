package com.mine.utils;

import com.mine.bean.WordBean;
import com.mine.bean.WordObj;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IOUtils {

    static WordBean[] switchMapToList(HashMap<String, Integer> map) {

        WordBean[] arrays = new WordBean[map.keySet().size()];
        int index = 0;
        for (String key : map.keySet()) {
            arrays[index] = new WordBean(key, map.get(key));
            index++;
        }
        Integer num = 0;
        //插入排序
        for (int i = 0; i < arrays.length; i++) {
            for (int j = i; j > 0 && arrays[j].getNum() < arrays[j - 1].getNum(); j--) {
                exchange(arrays, j, j - 1);
            }
        }
        return arrays;
    }

    static void exchange(WordBean[] arrays, int i, int j) {
        WordBean temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    /*
     * 对传进来的map进行转化后，按频率高低输出
     */
    public static void outPutData(String fileName, HashMap<String, Integer> map) {

        //对map中的数据进行排序,value值高的先打印
        WordBean[] arrays = switchMapToList(map);

        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File fOut = new File(fileName);

            if (!fOut.exists()) {
                fOut.createNewFile();
            } else {
                fOut.delete();
                fOut.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(fOut));

            for (int i = arrays.length - 1; i >= 0; i--) {
                bw.write("数量 : " + arrays[i].getNum() + " : " + arrays[i].getWord());
                bw.write("\n");
                bw.flush();
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

    public static void outPutData(String fileName, ArrayList<String> list) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        String info = "";
        try {
            File fOut = new File(fileName);

            if (!fOut.exists()) {
                fOut.createNewFile();
            } else {
                fOut.delete();
                fOut.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(fOut));

            for (String key : list) {
                bw.write(key);
                bw.write("\n");
                bw.flush();
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

    /*
        将3400个单词读入内存
     */
    public static List<String> read3400wordByFile(String fileName) {

        List<String> list = new ArrayList<>();
        FileInputStream fos = null;
        BufferedReader br = null;
        String line;
        try {
            File fInput = new File(fileName);

            br = new BufferedReader(new FileReader(fInput));
            while ((line = br.readLine()) != null) {
                String[] ss = line.split(":");
                String word = ss[ss.length - 1].trim();
                list.add(word);
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

        return list;
    }

    /*
        从考研词汇5500正序.txt文件中读取5500个标准单词
     */
    public static void readAllWordsAndAllMeansByFile(WordObj[] wordArray, List<String>[] meansArray) {

        FileInputStream fis = null;
        BufferedReader br = null;// 用于读取词汇的输入流

        String info = "";
        String[] strArray;
        String str;
        int index = 0;// 用来作为数组索引
        try {
            File fInput = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500正序.txt");

            // 读取词汇以及读取单词意思
            if (fInput.isFile() && fInput.canRead()) {
                br = new BufferedReader(new FileReader(fInput));
                while ((info = br.readLine()) != null) {

                    List<String> list = new LinkedList<String>();// 用来存放当前这一行的所有意思
                    meansArray[index] = list;

                    strArray = info.split(" ");// 拆分后的样式: 5376 whistle n.口哨,汽笛;口哨声,汽笛声 v.吹口哨;鸣笛
                    // 将单词分离出来
                    if (strArray != null && strArray.length > 1) {
                        WordObj obj = new WordObj();
                        obj.setWord(strArray[1]);
                        wordArray[index] = obj;
                        List<Integer> linkedList = new LinkedList<>();
                        obj.setList(linkedList);
                    }
                    // 将词组的意思分离出来，将每个意思前面的.去掉 , 同时再根据‘;’再进行一次拆分,最后还可以根据','再进行拆分
                    for (int i = 2; i < strArray.length; i++) {
                        str = strArray[i];// 得到单个意思群: n.口哨,汽笛;口哨声,汽笛声
                        // 开始进行进一步的拆分
                        str = str.substring(str.indexOf(".") != -1 ? str.indexOf(".") + 1 : 0);// 削减前面的部分: 口哨,汽笛;口哨声,汽笛声
                        str = str.substring(str.indexOf(".") != -1 ? str.indexOf(".") + 1 : 0);// 两次分割，为的就是除去里面的n./v.
                        String[] baseMeans = str.split(";");// 再进行进一步的拆分: 口哨,汽笛 口哨声,汽笛声
                        for (String mean : baseMeans) {
                            String[] bases;
                            if (mean.contains("，")) {
                                bases = mean.split("，");
                            } else {
                                bases = mean.split(",");// 再进行进一步的拆分: 口哨 汽笛
                            }
                            for (String b : bases) {
                                list.add(b);// 口哨
                            }
                        }
                    }

                    index++;
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
}
