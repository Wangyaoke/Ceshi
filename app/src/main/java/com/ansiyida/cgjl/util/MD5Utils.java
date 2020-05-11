package com.ansiyida.cgjl.util;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chenyaoxiang on 2017/11/07.
 * MD5加密工具类
 *
 * @author zhuming
 */
public class MD5Utils {

    /**
     * MD5加密文件
     * @param fileContent
     * @return
     */

    public static String md5File(String fileContent) {
        try {
            // 信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            StringBuffer buffer = new StringBuffer();
            byte[] result = digest.digest(fileContent.getBytes());
            for (byte b : result) {
                int number = b & 0xff;// 不按标准加密
                // 转换成16进制
                String numberStr = Integer.toHexString(number);
                if (numberStr.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(numberStr);
            }
            // MD5加密结果
            Log.i("tag", "md5---:" + buffer.toString());
            return buffer.toString();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }



    ///////////////////////////////////////////////////
    /**
     * 获取文件的MD5值
     * @param file
     * @return
     */
    public static String getFileMD5(File file){
        String md5 = null;
        FileInputStream fis = null;
        FileChannel fileChannel = null;
        try {
            fis = new FileInputStream(file);
            fileChannel = fis.getChannel();
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(byteBuffer);
                md5 = byteArrayToHexString(md.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileChannel.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return md5;
    }

    /**
     * 字节数组转十六进制字符串
     * @param digest
     * @return
     */
    private static String byteArrayToHexString(byte[] digest) {

        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<digest.length; i++){
            buffer.append(byteToHexString(digest[i]));
        }
        return buffer.toString();
    }

    public final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    /**
     * 字节转十六进制字符串
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        //  int d1 = n/16;
        int d1 = (b&0xf0)>>4;
        //   int d2 = n%16;
        int d2 = b&0xf;
        return hexDigits[d1] + hexDigits[d2];
    }
}
