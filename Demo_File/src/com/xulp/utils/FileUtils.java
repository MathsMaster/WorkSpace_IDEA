package com.xulp.utils;

import java.io.File;

/**
 * created by xulp
 * on 2022/9/21
 */
public class FileUtils {

    public static void deleteFileByPath(String pathName, String notDeleteName)
    {
        File file = new File(pathName);
        if(file.isDirectory())
        {
            deleteFile(file,notDeleteName);
        }else {
            return;
        }

    }

    /**
     * 递归删除文件
     * @param notDeleteName
     * @param file
     */
    static void deleteFile(File file,String notDeleteName)
    {
        if(file.isFile())//如果是文件就判断名称是否符合条件
        {
            if(file.getName().contains(notDeleteName))//就留下
            {

            }else {

                if(file.delete())
                    System.out.println("删除了文件 : "+file.getName());
            }
            return;

        }else if(file.isDirectory())//如果是文件夹，就继续进行递归操作
        {
            File [] files = file.listFiles();
            for(File f : files)
            {
                deleteFile(f,notDeleteName);
            }
        }
    }
}
