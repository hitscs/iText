package itext.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToPDF {
	public static void main(String[] args) {
		try {
			Document document = new Document(PageSize.LETTER);
			PdfWriter pdfWriter = PdfWriter.getInstance(document,
					new FileOutputStream("/home/lin/testpdf.pdf"));
			document.open();
			document.addAuthor("CelLoud");
			document.addCreator("lin");
			document.addSubject("HBV_SNP");
			document.addCreationDate();
			document.addTitle("HBV_SNP");

			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

			String str = FileUtils.readFileToString(new File(
					"/home/lin/21/82/20140905511093/HBV_SNP/HBV_SNP.html"));
			String css = FileUtils
					.readFileToString(new File(
							"/home/lin/21/82/20140905511093/HBV_SNP/support/css/SNP.css"));
			worker.parseXHtml(pdfWriter, document,
					new ByteArrayInputStream(str.getBytes()),
					new ByteArrayInputStream(css.getBytes()),
					new AsianFontProvider());
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
