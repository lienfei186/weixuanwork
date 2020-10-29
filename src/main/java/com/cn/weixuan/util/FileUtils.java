package com.cn.weixuan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Title FileUtil
 * @Package
 * @Description TODO
 * @Author cosco  coscofan@outlook.com
 * @Date 2020/5/2 0002 19:26
 */
public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
    private static final int BUFFER = 8192;
    /**
     * 功 能: 创建文件夹 参 数: strDir 要创建的文件夹名称 返回值: 如果成功true;否则false
     *
     * @param strDir
     * @return
     */
    public static boolean makeDir(String strDir) {
        File fileNew = new File(strDir);
        if (!fileNew.exists()) {
            return fileNew.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 功 能: 删除文件夹 参 数: strDir 要删除的文件夹名称 返回值: 如果成功true;否则false
     * @param strDir
     * @return
     */
    public static boolean removeDir(String strDir) {
        File rmDir = new File(strDir);
        if (rmDir.isDirectory() && rmDir.exists()) {
            String[] fileList = rmDir.list();
            for (int i = 0; i < fileList.length; i++) {
                String subFile = strDir + File.separator + fileList[i];
                File tmp = new File(subFile);
                if (tmp.isFile())
                    tmp.delete();
                else if (tmp.isDirectory())
                    removeDir(subFile);
            }
            rmDir.delete();
        } else {
            return false;
        }
        return true;
    }

    /**
     * 功 能: 删除指定的文件 参 数: 指定绝对路径的文件名 strFileName 返回值: 如果删除成功true否则false;
     *
     * @param strFileName
     * @return
     */
    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);

        if (!fileDelete.exists() || !fileDelete.isFile()) {
            log.debug(strFileName + "不存在!");
            return false;
        }

        return fileDelete.delete();
    }

    /**
     * copy文件到指定目录
     * @param strSourceFileName
     * @param strDestDir
     * @return
     */
    public static boolean copyTo(String strSourceFileName, String strDestDir) {
        File fileSource = new File(strSourceFileName);
        File fileDest = new File(strDestDir);

        // 如果源文件不存或源文件是文件夹
        if (!fileSource.exists() || !fileSource.isFile()) {
            log.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
            return false;
        }

        // 如果目标文件夹不存在
        if (!fileDest.isDirectory() || !fileDest.exists()) {
            if (!fileDest.mkdirs()) {
                log.debug("目录文件夹不存，在创建目标文件夹时失败!");
                return false;
            }
        }

        try {
            String strAbsFilename = strDestDir + File.separator + fileSource.getName();

            FileInputStream fileInput = new FileInputStream(strSourceFileName);
            FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

            log.debug("开始拷贝文件");

            int count = -1;

            long nWriteSize = 0;
            long nFileSize = fileSource.length();

            byte[] data = new byte[BUFFER];

            while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

                fileOutput.write(data, 0, count);

                nWriteSize += count;

                long size = (nWriteSize * 100) / nFileSize;
                long t = nWriteSize;

                String msg = null;

                if (size <= 100 && size >= 0) {
                    msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
                    log.debug(msg);
                } else if (size > 100) {
                    msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
                    log.debug(msg);
                }

            }

            fileInput.close();
            fileOutput.close();

            log.debug("拷贝文件成功!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移动文件(只能移动文件) 参 数: strSourceFileName: 是指定的文件全路径名 strDestDir:
     * 移动到指定的文件夹中 返回值: 如果成功true; 否则false
     *
     * @param strSourceFileName
     * @param strDestDir
     * @return
     * @Title: moveFile
     * @Description: TODO
     * @return: boolean
     */
    public static boolean moveFile(String strSourceFileName, String strDestDir) {
        if (copyTo(strSourceFileName, strDestDir))
            return delete(strSourceFileName);
        else
            return false;
    }


}
