package itext.test;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class T {
	public static void main(String[] args) {
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"/home/lin/Table3.pdf"));
			document.open();

			PdfPTable table = new PdfPTable(4); // 3 columns.

			Image image = Image.getInstance("/home/lin/portrait.png");
			PdfPCell cell1 = new PdfPCell(image);
			PdfPCell cell2 = new PdfPCell(image);


			PdfPCell cell3 = new PdfPCell(image);
			PdfPCell cell4 = new PdfPCell(image);
			table.addCell(cell1);
			table.addCell(cell2);

			table.addCell(cell3);
			table.addCell(cell4);

			document.add(table);

			document.close();
		} catch (Exception e) {

		}
	}
}
