package com.mine.utils;

import com.mine.bean.WordObj;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xulp
 * <p>
 * 实现思路：
 * 1，准备两个数组，WordArray长度6000，存放的是WordObj对象，该对象包含了单词，以及一个集合对象list<Integer>;
 * 2，使用IO流把所有单词全都读出来，同时把后面的单词意思也都读出来；
 *      1>使用一个一维数组WordArray来把所有的单词放进去，这里放进去的是个对象，包含了单词以及一个集合对象list<Integer>(用来标记有多少单词和当前词组存在相关性)
 *      2>再使用一个二维数组MeansArray，把所有单词的意思都拆开，同时放在一个二维数组里面，因为一个单词最多也就5，6个意思，所以，数组的长度只需要6就够了；对单词意思拆分后进行过滤后，放置在对应数组位置
 * 3，使用二维数组MeansArray的第一个子数组的第一个意思，与其他所有单词的意思进行匹配，匹配上的就在WordArray中的对应的第一个list中进行标记；再使用二维数组MeansArray的第一个字数组的第二个意思，与其他所有单词的意思进行匹配，能匹配上的就继续在WordArray中的第一个list进行标记。。。。。
 * 4，如果第一个数组匹配完，则使用第二个数组,重复上述步骤。。。。。。
 * 5，当5500个数组都完成了上述步骤后，必定会出现，其中某一个单词对象的list<Integer>，添加了很多被标记对象的索引
 * 6，这样子就好出现另一个问题，你标记过我的，那我也会标记你； 。。。。。。。
 */
public class Utils {

    private static WordObj[] wordArray;
    private static List<String>[] meansArray;// 数组里放的全是List集合，集合里放的又都是String
    final static int WordMAXNum = 6000;
//	final static int rowNum = 10;

    public static void main(String[] args) {

        init();
        readAllWordsAndAllMeans(wordArray, meansArray);// 读取单词
        match(wordArray, meansArray);// 读取单词
//		test(wordArray, meansArray);
        testOutPutFile(wordArray);
        printAllWord(wordArray);// 把有关联的单词都打印到一起，必然会出现很多重复的单词
    }

    static List<String> readAllColum() {
        List<String> list = new LinkedList<String>();// 用来存放当前这一行的所有意思
        FileInputStream fis = null;
        BufferedReader br = null;// 用于读取词汇的输入流

        String info = "";
        try {
            File fInput = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500正序.txt");

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

    /*
     * 根据单词中已经匹配成功的索引，来把具有同样意思的都打印在一起 思路:先将所有的行全部读取出来，然后存放在一个list数组中，然后根据一个索引来进行打印
     */
    static void printAllWord(WordObj[] wordArray) {
        List<String> allStrList = readAllColum();// 先读取出所有的行来

        List<String> alreadyPrintList = new ArrayList<String>();

        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File fOut = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500的数据进行关联后的结果.txt");

            if (!fOut.exists()) {
                fOut.createNewFile();
            } else {
                fOut.delete();
                fOut.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(fOut));

            String tempStr = "";
            for (int i = 0; i < wordArray.length; i++) {
                //  先打印自己这一行
                if (allStrList.size() > i)
                {
                    tempStr = "";
                    tempStr = allStrList.get(i);
                    tempStr = cuttingStr(tempStr);
                    if(alreadyPrintList.contains(tempStr))//表示已经打印过了，不需要再进行打印
                        continue;
                    alreadyPrintList.add(tempStr);
                    bw.write( tempStr+ "\n");
                }
                bw.flush();

                //  再打印关联行的意思
                if (wordArray[i] != null && wordArray[i].getList() != null) {
                    List<Integer> list = wordArray[i].getList();
                    Integer index = 0;
                    for (int j = 0; j < list.size(); j++) {
                        index = list.get(j);
                        if (allStrList.size() > index)//得在这里加个判断才行,剔除掉之前以及打印过的数据才行
                        {
                            tempStr = allStrList.get(index);
                            tempStr = cuttingStr(tempStr);
                            alreadyPrintList.add(tempStr);
                            bw.write(tempStr + "\n");
                        }
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


    static String cuttingStr(String str){
        if(str == null || str.equals(""))
            return "";
        String num = str.substring(0,str.indexOf(" "));
        int i = 0;
        try{
            i = Integer.parseInt(num);
        }catch (Exception e)
        {

        }
        if(i != 0)//不等于0的话就切割
            str  = str.substring(str.indexOf(" ")+1);
        return str;
    }

    static void testOutPutFile(WordObj[] wordArray) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        String info = "";
        try {
            File fOut = new File("/Users/xulp/Documents/学习资料/考研英语/考研英语词汇/考研词汇5500的统计数据.txt");

            if (!fOut.exists()) {
                fOut.createNewFile();
            } else {
                fOut.delete();
                fOut.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(fOut));

            for (int i = 0; i < wordArray.length; i++) {
                if (wordArray[i] != null && wordArray[i].getList() != null) {
                    List<Integer> list = wordArray[i].getList();
                    List<String> listStr = wordArray[i].getTempStr();
                    bw.write("第 " + i + " 项 " + wordArray[i].getWord() + " 匹配到的 : ");
                    bw.flush();
                    for (int j = 0; j < list.size(); j++) {
                        Integer index = list.get(j);
                        String str = listStr.get(j);
                        bw.write("  " + index + "(" + str + ")");
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

    /*
     * 进行各个词组意思的匹配 3，使用二维数组MeansArray的第一个子数组的第一个意思，与其他所有单词的意思进行匹配，
     * 匹配上的就在WordArray中的对应的第一个list中进行标记；再使用二维数组MeansArray的第一个字数组的第二个意思，
     * 与其他所有单词的意思进行匹配，能匹配上的就继续在WordArray中的第一个list进行标记。。。。。
     * 4，如果第一个数组匹配完，则使用第二个数组,重复上述步骤。。。。。。
     * 5，当5500个数组都完成了上述步骤后，必定会出现，其中某一个单词对象的list<Integer>，添加了很多被标记对象的索引
     */
    static void match(WordObj[] wordArray, List<String>[] meansArray) {
        for (int index = 0; index < meansArray.length; index++) {
            if (meansArray[index] != null && wordArray[index] != null) {
                List<String> list = meansArray[index];
                for (String mean : list)// 挨个取出当前单词的所有意思，和其他词组的所有意思一个个的进行匹配
                {
                    // 循环取出其他的词的各个意思
                    for (int i = index + 1; i < meansArray.length; i++) {
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
                                        //
                                        wordArray[index].getTempStr().add(mean);
                                        wordArray[i].getTempStr().add(mean);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static void test(WordObj[] wordArray, List<String>[] meansArray) {
        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] != null) {
                System.out.print("第 " + i + " 项匹配到的 : ");
                for (Integer index : wordArray[i].getList()) {
                    System.out.print(" " + index);
                }
                System.out.println();
            }
        }

//		for (List<String> list : meansArray) {
//			if (list != null) {
//				for (int i = 0; i < list.size(); i++) {
//					System.out.print(" " + list.get(i));
//				}
//				System.out.println();
//			}
//		}
    }

    static void init() {
        wordArray = new WordObj[WordMAXNum];
        meansArray = new LinkedList[WordMAXNum];

    }

    /*
     * 用于读取文件中的所有单词，并且放进一个数组中 并且将意思读取到另一个二维数组里 2，使用IO流把所有单词全都读出来，同时把后面的单词意思也都读出来；
     * 1>使用一个一维数组WordArray来把所有的单词放进去，这里放进去的是个对象，包含了单词以及一个集合对象list<Integer>(
     * 用来标记有多少单词和当前词组存在相关性)
     * 2>再使用一个二维数组MeansArray，把所有单词的意思都拆开，同时放在一个二维数组里面，因为一个单词最多也就5，6个意思，所以，
     * 数组的长度只需要6就够了；对单词意思拆分后进行过滤后，放置在对应数组位置
     */
    static void readAllWordsAndAllMeans(WordObj[] wordArray, List<String>[] meansArray) {

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

                        List<String> tempListStr = new LinkedList<>();
                        obj.setTempStr(tempListStr);
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