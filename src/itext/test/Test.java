package itext.test;

import itext.service.HBV;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class Test {
	public static void main(String[] args) {
		try {
			HBV.createPDF("/home/lin/21/82/20140905511093/SVG/", "HBV2");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// try {
		// Other.one();
		// } catch (DocumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
