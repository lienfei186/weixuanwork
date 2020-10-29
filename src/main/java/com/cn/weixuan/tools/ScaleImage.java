package com.cn.weixuan.tools;

import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author ����
 * @time 2008-1-10
 */
public class ScaleImage {
	/**
	 * ͼ��ɱ�������
	 * 
	 * @param sourcePath
	 *            Դͼ�����·����
	 * @param targetPath
	 *            Ŀ��ͼ�����·����
	 * @param width
	 *            Ŀ��ͼ�Ŀ�����ֵ��
	 * @param height
	 *            Ŀ��ͼ�ĸ߶����ֵ��
	 * @param hints
	 *            ���»�ͼʹ�õ� RenderingHints ����
	 * @throws IOException
	 */
	public static void scaleJ2D(String sourcePath, String targetPath, int width, int height, RenderingHints hints) throws IOException {
		BufferedImage sourceImage = ImageIO.read(new File(sourcePath));

		int w = sourceImage.getWidth();
		int h = sourceImage.getHeight();
		double scale;
		int nw;
		int nh;

		// ��Դͼ���߱���������ָ��������ֵ�ķ�Χ�ڵ�Ŀ��ͼ�������ֵ�����ű���
		if ((double) height / width <= (double) h / w) {
			scale = (double) height / h;
			nw = (int) ((double) height * w / h);
			nh = height;
		} else {
			scale = (double) width / w;
			nw = width;
			nh = (int) ((double) width * h / w);
		}

		if (hints == null) {
			hints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
		}

		AffineTransform transform = new AffineTransform();
		transform.setToScale(scale, scale);

		AffineTransformOp ato = new AffineTransformOp(transform, null);
		BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
		ato.filter(sourceImage, bid);

		ImageIO.write(bid, sourcePath.substring(sourcePath.lastIndexOf(".") + 1), new File(targetPath));
	}

	public static void main(String[] ss) throws IOException {
		String sourcePic = "C:/my.jpg";
		String sourceS = "C:/my_s.jpg";
		File file= new File(sourceS);
		String pp=file.getPath();
		String aaa=file.getName();
		
		ScaleImage.scaleJ2D(sourcePic, sourceS, 130, 130, null);
	}
}
