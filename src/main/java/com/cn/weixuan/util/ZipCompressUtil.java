package com.cn.weixuan.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZipCompressUtil
{
     private static Logger logger = LoggerFactory.getLogger(ZipCompressUtil.class);
//    private String zipFileName;      // 目的地Zip文件
//    private String sourceFileName;   //源文件（带压缩的文件或文件夹）

    public ZipCompressUtil()
    {

    }

/*    public void zip() throws Exception
    {
        //File zipFile = new File(zipFileName);
        log.info("压缩中...");

        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream( new FileOutputStream(zipFileName));

        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);

        File sourceFile = new File(sourceFileName);

        //调用函数
        compress(out,bos,sourceFile,sourceFile.getName());

        bos.close();
        out.close();
        log.info("压缩完成");

    }*/

    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(List<File> srcFiles, OutputStream out) throws Exception {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[10 * 1024];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len = 0;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.flush();
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            logger.info("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new Exception("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void compress(ZipOutputStream out,BufferedOutputStream bos, File sourceFile, String base) throws Exception
    {
        //如果路径为目录（文件夹）
        if(sourceFile.isDirectory())
        {

            //取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();

            if(flist.length==0)//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
            {
                log.info(base+"/");
                out.putNextEntry(  new ZipEntry(base+"/") );
            }
            else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for(int i=0;i<flist.length;i++)
                {
                    compress(out,bos,flist[i],base+"/"+flist[i].getName());
                }
            }
        }
        else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        {
            out.putNextEntry( new ZipEntry(base) );
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);

            int tag;
            log.info(base);
            //将源文件写入到zip文件中
            while((tag=bis.read())!=-1)
            {
                bos.write(tag);
            }
            bis.close();
            fos.close();

        }
    }

    public static void downLoadZip(String fileName, HttpServletResponse response, File sourceFile) throws Exception {
        ServletOutputStream out = null;
        FileInputStream fis = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName , "UTF-8") + "\"");
            logger.info("导出文件名称: " + response.getHeader("Content-Disposition"));
            out = response.getOutputStream();
            fis = new FileInputStream(sourceFile);
            byte[] buffer = new byte[1024 * 10];
            int len = 0;
            while((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
        }
        catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new Exception("下载文件失败");
        }
        finally {
//            if (out != null) {
//                out.flush();
//                out.close();
//            }
            if (fis != null) {
                fis.close();
            }

        }
    }

}


