package com.cn.weixuan.tools;

import java.io.File;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
public class UtilFile {
	private final static Logger LOGGER = LoggerFactory.getLogger(UtilFile.class);

	public static void main(String[] ss) {
		new File("C:\\").delete();
	}
	/**
	 * 删除文件，如果所在文件夹下只有一个文件，那么递归删除所在文件夹。
	 * 
	 * @param file
	 */
	public static void deleteFileAndParentDir(File file) {
		File dir = file.getParentFile();
		file.delete();
		if (dir.exists() && dir.list().length == 0) {
			deleteFileAndParentDir(dir);
		}
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
	 * 获得文件后缀名,例:bmp
	 * 
	 * @return
	 */
	public static String getFilePostfix(String filePath) {
		String name = null;
		if (filePath.contains(".")) {
			String[] ss = filePath.split("\\.");
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
	 * @param file
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
			int postFix = 0;// 当前循环对比中，最大的文件名称数字，例：未命名(3),此时postFix=3
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
								e.printStackTrace();
								LOGGER.error("getNoRepeatFileName()，命名文件错误。" + e.getMessage());
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
