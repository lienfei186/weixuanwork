package com.cn.weixuan.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

public class ToolFile {

	public static File saveFile(InputStream inputStream, String targetPath) throws IOException {
		File targetFile = getNoRepeatFileName(new File(targetPath));
		java.nio.file.Files.copy(inputStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		return targetFile;
	}

	/**
	 * 获得文件名，不带后缀，不带路径
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileNameNoPostfix(File file) {
		String name = file.getName();
		if (file.getName().contains(".")) {
			String[] ss = name.split("\\.");
			// 文件名不带扩展名
			name = ss[0];
		}
		return name;

	}

	public static File getSmallPic(File _file) {
		String savedImageFileSmall = _file.getPath().split("\\.") + "_s.bmp";
		return new File(savedImageFileSmall);
	}

	/**
	 * 获得文件后缀名,例:.bmp
	 * 
	 * @return
	 */
	public static String getFilePostfix(File file) {
		String name = file.getName();
		if (file.getName().contains(".")) {
			String[] ss = file.getName().split("\\.");
			// 文件名不带扩展名
			name = ss[1];
			return name;
		}
		return null;
	}

	/**
	 * 获得文件路径，不带文件名
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileDir(File file) {
		String path = file.getPath();
		// 文件路径，不带文件名
		String realPath = path.substring(0, path.lastIndexOf(file.getName()));

		return realPath;
	}

	/**
	 * 获得文件，如果重名，文件名后面自动加(1),数字顺序增加
	 * 
	 * @param savedImageFile
	 * @return
	 */
	public static File getNoRepeatFileName(File file) {
		if (file.exists()) {
			// 原始文件名,取出来当前文件名不带自动增加的_1
			String fileNameOriginal = getFileNameNoPostfix(file);
			if (fileNameOriginal.contains("(")) {
				fileNameOriginal = fileNameOriginal.substring(0, fileNameOriginal.lastIndexOf("("));
			}
			String dir = getFileDir(file);
			File dirF = new File(dir);
			File[] list_file = dirF.listFiles();
			int count = 0;
			int postFix = 0;// 重名文件默认起始增加的文件名
			// 顺序检查要保存的文件路径下所有文件
			while (true) {
				File _file = list_file[count];
				// 只检查文件，不检查文件夹
				if (_file.isFile()) {
					String _fileName = getFileNameNoPostfix(_file);
					// 找到跟当前要保存的文件重名的一堆文件，才去处理
					if (_fileName.contains(fileNameOriginal)) {
						if (_fileName.contains("(")) {
							String num = _fileName.substring(_fileName.lastIndexOf("(") + 1, _fileName.lastIndexOf(")"));
							int cur_num = 0;
							try {
								cur_num = Integer.valueOf(num);
								if (cur_num - postFix > 0) {
									postFix = cur_num;
								}
							} catch (Exception e) {

							}
						}
					}
				}
				// 检查完所有文件，退出循环
				if (count == list_file.length - 1) {
					break;
				}
				count++;
			}
			String newFileName = getFileDir(file) + fileNameOriginal + "(" + ++postFix + ")." + getFilePostfix(file);
			return new File(newFileName);
		}
		return file;
	}
}
