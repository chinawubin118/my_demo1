package com.lotte.lottelibrary.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Lotte on 2016/8/27.
 * 与文件,IO操作相关的工具函数
 */
public class FileUtil {
    private static String filenameTemp;

    /**
     * 创建文件
     */
    public static boolean creatTxtFile(String path, String name) throws IOException {
        boolean flag = false;
        filenameTemp = path + "/" + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }
        return flag;
    }

    /**
     * 写文件
     */
    public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            throw e1;
        } finally {
            if (pw != null) pw.close();
            if (fos != null) fos.close();
            if (br != null) br.close();
            if (isr != null) isr.close();
            if (fis != null) fis.close();
        }
        return flag;
    }

    /**
     * 创建文件夹
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            Log.i("FileUtil", "创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            Log.i("FileUtil", "创建目录" + destDirName + "成功！");
            return true;
        } else {
            Log.i("FileUtil", "创建目录" + destDirName + "失败！");
            return false;
        }
    }

    private OnDownLoadProgressChange onDownLoadProgressChange;

    public void setOnDownLoadProgressChange(OnDownLoadProgressChange onDownLoadProgressChange) {
        this.onDownLoadProgressChange = onDownLoadProgressChange;
    }

    public interface OnDownLoadProgressChange {
        public void onProgressChanged(int currentSize);//当前下载的总大小

        public void onGetFileSize(int fileSize);//文件的大小
    }

    /**
     * 下载文件到本地
     *
     * @param urlString 被下载的文件地址
     * @param filename  本地文件名
     * @throws Exception 各种异常
     */
    public void downloadWordListen(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //回传文件的大小
        if (null != onDownLoadProgressChange && null != con) {//
            onDownLoadProgressChange.onGetFileSize(con.getContentLength());
        }
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        //下载的总大小
        int downloadSize = 0;
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
            downloadSize += len;
            if (null != onDownLoadProgressChange) {// && downloadSize % (1000 * 1024) == 0
                onDownLoadProgressChange.onProgressChanged(downloadSize);
            }
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}

