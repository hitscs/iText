package itext.utils;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class WatermarkUtil {
	/**
	 * 为PDF插入水印工具类
	 * 
	 * @param sourceFile
	 *            ：目标pdf文件
	 * @param resultFile
	 *            ：结果pdf文件
	 * @param imagePath
	 *            ：水印图片位置
	 * @param pageSize
	 *            ：从第一页开始，共几页需要水印
	 * @param positionX
	 *            ：水印位置的x轴
	 * @param positionY
	 *            ：水印位置的y轴
	 * @throws Exception
	 */
	public static void addMark(String sourceFile, String resultFile,
			String imagePath, int pageSize, int positionX, int positionY)
			throws Exception {
		PdfReader reader = new PdfReader(sourceFile, "PDF".getBytes());
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
				resultFile));
		Image img = Image.getInstance(imagePath);// 插入水印
		img.scaleAbsolute(20f, 20f); // 设置图片大小
		img.setAbsolutePosition(positionX, positionY);
		for (int i = 1; i <= pageSize; i++) {
			PdfContentByte under = stamp.getUnderContent(i);
			under.addImage(img);
		}
		stamp.close();// 关闭
		File tempfile = new File(sourceFile);
		if (tempfile.exists()) {
			tempfile.delete();
		}
	}
}