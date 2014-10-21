package itext.test;

import itext.main.HBV_SNP_PDF;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class Test {
	public static void main(String[] args) {
		try {
			HBV_SNP_PDF
					.createPDF("/home/lin/21/82/20140905511093/SVG/", "HBV2");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
