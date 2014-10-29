package itext.test;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Other {
	public static void one() throws DocumentException, IOException {
		Document document = new Document();
		BaseFont bfChinese = BaseFont.createFont("STSong-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font FontChinese = new Font(bfChinese, 14, Font.BOLD);

		PdfWriter
				.getInstance(document, new FileOutputStream("/home/lin/T.pdf"));
		document.open(); // 打开文档
		// ------------开始写数据-------------------
		Paragraph title = new Paragraph("起租通知书", FontChinese);// 抬头
		title.setAlignment(Element.ALIGN_CENTER); // 居中设置
		title.setLeading(1f);// 设置行间距//设置上面空白宽度
		document.add(title);

		title = new Paragraph("致：XXX公司", FontChinese);// 抬头
		title.setSpacingBefore(25f);// 设置上面空白宽度
		document.add(title);

		title = new Paragraph(
				"         贵我双方签署的编号为 XXX有关起租条件已满足，现将租赁合同项下相关租赁要素明示如下：",
				FontChinese);
		title.setLeading(22f);// 设置行间距
		document.add(title);

		float[] widths = { 10f, 25f, 30f, 30f };// 设置表格的列宽和列数 默认是4列
		widths = new float[] { 8f, 15f, 15f, 15f, 15f, 16f, 16f };

		PdfPTable table = new PdfPTable(widths);// 建立一个pdf表格
		table.setSpacingBefore(20f);// 设置表格上面空白宽度
		table.setTotalWidth(500);// 设置表格的宽度
		table.setWidthPercentage(100);// 设置表格宽度为%100
		// table.getDefaultCell().setBorder(0);//设置表格默认为无边框

		String[] tempValue = { "1", "2011-07-07", "2222", "11.11", "11.11",
				"3000", "9999" }; // 租金期次列表
		int rowCount = 1; // 行计数器
		PdfPCell cell = null;
		// ---表头
		cell = new PdfPCell(new Paragraph("期次", FontChinese));// 描述
		cell.setFixedHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("租金日", FontChinese));// 描述
		cell.setFixedHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("各期租金金额", FontChinese));// 描述
		cell.setFixedHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		table.addCell(cell);

		cell = new PdfPCell(new Paragraph("各期租金后\n剩余租金", FontChinese));// 描述
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
		cell.setFixedHeight(20);
		table.addCell(cell);

		for (int j = 1; j < tempValue.length; j++) {
			if (j % 2 == 1) { // 第一列 日期
				cell = new PdfPCell(new Paragraph(rowCount + "", FontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				rowCount++;
			}
			cell = new PdfPCell(new Paragraph(tempValue[j], FontChinese));// 描述
			cell.setFixedHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
			table.addCell(cell);
		}
		document.add(table);

		title = new Paragraph("                租金总额：XXX", FontChinese);
		title.setLeading(22f);// 设置行间距
		document.add(title);
		title = new Paragraph("         特此通知！", FontChinese);
		title.setLeading(22f);// 设置行间距
		document.add(title);
		// -------此处增加图片和日期，因为图片会遇到跨页的问题，图片跨页，图片下方的日期就会脱离图片下方会放到上一页。
		// 所以必须用表格加以固定的技巧来实现
		float[] widthes = { 50f };// 设置表格的列宽和列数
		PdfPTable hiddenTable = new PdfPTable(widthes);// 建立一个pdf表格
		hiddenTable.setSpacingBefore(11f); // 设置表格上空间
		hiddenTable.setTotalWidth(500);// 设置表格的宽度
		hiddenTable.setWidthPercentage(100);// 设置表格宽度为%100
		hiddenTable.getDefaultCell().disableBorderSide(1);
		hiddenTable.getDefaultCell().disableBorderSide(2);
		hiddenTable.getDefaultCell().disableBorderSide(4);
		hiddenTable.getDefaultCell().disableBorderSide(8);

		Image upgif = Image.getInstance("/home/lin/portrait.png");
		upgif.scalePercent(7.5f);// 设置缩放的百分比%7.5
		upgif.setAlignment(Element.ALIGN_RIGHT);

		cell = new PdfPCell(new Paragraph("", FontChinese));// 描述
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);// 设置内容水平居中显示
		cell.addElement(upgif);
		cell.setPaddingTop(0f); // 设置内容靠上位置
		cell.setPaddingBottom(0f);
		cell.setPaddingRight(20f);
		cell.setBorder(Rectangle.NO_BORDER);// 设置单元格无边框
		hiddenTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("XX 年 XX 月 XX 日                    ",
				FontChinese));// 金额
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);// 设置内容水平居中显示
		cell.setPaddingTop(0f);
		cell.setPaddingRight(20f);
		cell.setBorder(Rectangle.NO_BORDER);
		hiddenTable.addCell(cell);
		document.add(hiddenTable);
		System.out.println("拼装起租通知书结束...");
		document.close();

	}

	public static void two() throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter.getInstance(document,
				new FileOutputStream("/home/lin/xx.pdf"));
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", true);
		Font fontChinese = new Font(bfChinese, 12, Font.NORMAL, BaseColor.BLACK);
		document.open();
		Paragraph par = new Paragraph("你好", fontChinese);
		document.add(par);
		Paragraph parE = new Paragraph("welcome", new Font());
		document.add(parE);
		document.close();
	}
	public static void main(String[] args) throws DocumentException,
			IOException {
		one();
	}
}
