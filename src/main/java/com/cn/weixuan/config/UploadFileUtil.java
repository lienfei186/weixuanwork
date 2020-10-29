package com.cn.weixuan.config;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadFileUtil {
    /**
     * 多张/单张都可以用这个 保存图片
     *
     * @param files 要批量上传的文件
     * @param path  图片保存的路径
     * @return "wrong_file_extension"-错误的后缀, "file_empty"-空文件 或者 保存后的绝对路径
     */
    public static List<String> uploadFiles(List<MultipartFile> files, String path) throws IOException {
        List<String> msgs = new ArrayList<>();
        if (files.size() < 1) {
            msgs.add("file_empty");

            return msgs;
        }
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                String type = filename.indexOf(".") != -1
                        ? filename.substring(filename.lastIndexOf("."), filename.length())
                        : null;
                if (type == null) {
                    msgs.add("file_empty");
                    return msgs;
                }

              /*  if (!(".PNG".equals(type.toUpperCase()) || ".JPG".equals(type.toUpperCase()))) {
                    msgs.add("wrong_file_extension");
                    return msgs;
                }*/
            }
        }
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);

            String filepath = path + System.currentTimeMillis() + file.getOriginalFilename();
            File filesPath = new File(path);
            if (!filesPath.exists()) {
                filesPath.mkdir();
            }
            BufferedOutputStream out = null;

            try {
                out = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                out.write(file.getBytes());
                msgs.add(filepath);
            } catch (Exception e) {
                // 没有上传成功
                e.printStackTrace();
            } finally {
                out.flush();
                out.close();
            }
        }
        return msgs;
    }

    public static Boolean deleteFile(String paths) {
        String s1 = StringUtil.trimFirstAndLastChar(paths, '[');
        String s2 = StringUtil.trimFirstAndLastChar(s1, ']');
        String s3 = s2.replace(" ", "");
        String[] a = s3.split(",");
        Boolean flag = false;
        for (int i = 0; i < a.length; i++) {
            File file = new File(a[i]);
            //判断文件是否存在
            if (file.exists() == true) {

                flag = file.delete();

            } else {
                return false;
            }
        }
        return flag;
    }
}