package com.cn.weixuan.tools;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * 本体图片加载到内存,以及调整图片大小
 * @author hh
 */
public class ImageUtils {

	/**
	 * @param bImage
	 * @param savedImageFilePath
	 *            例如:savedImageFilePath = "c:\\my.bmp";
	 * @throws IOException
	 */
	public static File saveImage(BufferedImage bImage, String savedImageFilePath) throws IOException {
		File file = new File(savedImageFilePath);
		ImageIO.write(bImage, "bmp", file);
		return file;
	}
	public static File saveImageJPEG(BufferedImage bImage, String savedImageFilePath) throws IOException {
		File file = new File(savedImageFilePath);
		ImageIO.write(bImage, "jpg", file);
		return file;
	}
	/**
	 * 缩放图片
	 * 
	 * @param imageIcon
	 * @param width
	 * @param height
	 * @return
	 */
	public static ImageIcon setImgSize(ImageIcon imageIcon, int width, int height) {
		Image image = imageIcon.getImage(); // 但这个图片太大不适合做Icon
		// 为把它缩小点，先要取出这个Icon的image ,然后缩放到合适的大小
		Image smallImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
		// 再由修改后的Image来生成合适的Icon
		ImageIcon Icon = new ImageIcon(smallImage);
		return Icon;
	}

	/**
	 * 加载本地图片
	 * 
	 * @param imgstr
	 * @return
	 */
	public static ImageIcon useBMP(String imgstr) {
		ImageIcon ic = null;
		try {
			BufferedImage bi = ImageIO.read(new File(imgstr));
			ImageProducer producer = bi.getSource();
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = toolkit.createImage(producer);
			ic = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ic;
	}

	/**
	 * 加载本地图片
	 * 
	 * @param imgPath
	 * @return
	 */
	public static byte[] readImageInfo(String imgPath) {
		File bmpfile = new File(imgPath);
		if (!bmpfile.exists()) {
			return null;
		}
		BufferedImage img = null;
		ByteArrayOutputStream bf = null;
		try {
			bf = new ByteArrayOutputStream();
			img = ImageIO.read(bmpfile);
			ImageIO.write(img, "bmp", bf);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return bf.toByteArray();
	}

	/**
	 * 加载本地图片
	 * 
	 * @param path
	 * @return
	 */
	public static byte[] getPhoto(String path) {
		File bmpfile = new File(path);
		if (!bmpfile.exists()) {
			return null;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) bmpfile.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(bmpfile));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while ((len = in.read(buffer, 0, buf_size)) != -1) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bos.toByteArray();
	}

	/**
	 * byte[]图片转Image
	 * 
	 * @param buf
	 * @return
	 */
	public static Image byteToImage(byte[] buf) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(buf));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * 获取当前jar包路径
	 * 
	 * @return
	 */
	public static String getProjectPath() {
		java.net.URL url = ImageUtils.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = null;
		try {
			filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (filePath.endsWith(".jar"))
			filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
		File file = new File(filePath);
		filePath = file.getAbsolutePath();
		return filePath;
	}
	
	/**
	 * 获得患者报告图片的文件夹路径=web.upload-path静态资源路径下，当天时间+患者名称_患者no_患者id
	 * 
	 * @param patient_no
	 * @param patient_name
	 * @return
	 */
	public static String getPatientPicDir(String configPath,String doctor_login_name,String patient_no, String patient_name, String examItem) {
		// 患者pic文件夹名称
		String picdir = patient_no + "_" + patient_name + "_" + examItem;
		// 如果身份证号没有
		if (patient_no == null || "".equals(patient_no)) {
			picdir = "__" + patient_name;
		}
		String date = ToolDateTime.getYYYYMMDDNumber(new Date());
		// 存在服务器上的真正路径
		String dirToday = configPath + date;
		File dirTodayFile = new File(dirToday);
		if (!dirTodayFile.exists()) {
			dirTodayFile.mkdirs();
		}
		String picPath = dirToday + "/"  + doctor_login_name + "/" + picdir + "/";
		File filedir = new File(picPath);
		if (!filedir.exists()) {
			filedir.mkdirs();
		}
		return picPath;
	}

}
