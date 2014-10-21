package itext.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @Description:文件操作工具类
 * @author lin
 * @date 2013-7-29 下午7:36:51
 */
public class FileTools {

	public static int countFileLines(String path) {
		File test = new File(path);
		long fileLength = test.length();
		LineNumberReader rf = null;
		int lines = 0;
		try {
			rf = new LineNumberReader(new FileReader(test));
			if (rf != null) {
				rf.skip(fileLength);
				lines = rf.getLineNumber();
				rf.close();
			}
		} catch (IOException e) {
			if (rf != null) {
				try {
					rf.close();
				} catch (IOException ee) {
				}
			}
		}
		return lines;
	}

	public static int countLines(String filePath) {
		BufferedReader br = null;
		int count = 0;
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while (br.readLine() != null) {
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public static int countLines(File file) {
		BufferedReader br = null;
		int count = 0;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			while (br.readLine() != null) {
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	/**
	 * 获取文件的最后一行
	 * 
	 * @note 该方法只适用小文件
	 * @param filePath
	 * @return
	 */
	public static String getLastLine(String filePath) {
		FileReader in = null;
		try {
			in = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		LineNumberReader reader = new LineNumberReader(in);
		String s = null;
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if (!"".equals(line.trim())) {
					s = line;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 获取文件的第一行
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFirstLine(String filePath) {
		if (!new File(filePath).exists()) {
			return null;
		}
		FileReader in = null;
		try {
			in = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		LineNumberReader reader = new LineNumberReader(in);
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * 样本名称去重复，过滤 sampleList 中样本名称
	 * 
	 * @param sampleList
	 * @return ：HashSet<String>
	 */
	public static HashSet<String> delRepeat(String sampleList) {
		String dataArray[] = sampleList.split(";");
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < dataArray.length; i++) {
			set.add(dataArray[i].split(":")[0]);
		}
		return set;
	}

	/**
	 * 读取报告
	 * 
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	public static String readAppoint(String sourceFile) {
		String context = null;
		try {
			context = FileUtils.readFileToString(new File(sourceFile), "GBK");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return context == null ? "" : context.replaceAll("\n", "<br />");
	}

	/**
	 * 创建目录，若目录已存在，则清空
	 * 
	 * @param path
	 *            ：要创建的目录路径
	 */
	public static void createDir(String path) {
		File file = new File(path);
		if (file.exists()) {
			String[] fileName = file.list();
			for (int i = 0; i < fileName.length; i++) {
				String name = path + "/" + fileName[i];
				new File(name).delete();
			}
		}
		file.mkdirs();
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            ： 路径格式若为：/home/down/test.txt，
	 *            若路径不存在，则生成home/down文件夹后生成test.txt文件
	 */
	public static void createFile(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("文件创建异常：" + e);
		}
	}

	/**
	 * 向文件中追加内容
	 * 
	 * @param filePath
	 *            ：要写入的文件
	 * @param writeContext
	 *            ：要追加的内容
	 * @throws IOException
	 */
	public static void appendWrite(String filePath, String writeContext){
		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath, true);
			fw.write(writeContext);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断文件夹下是否有符合规则的文件，有则返回文件名，无则返回 ""
	 * 
	 * @param folderPath
	 *            ：要查询的文件夹
	 * @param regulation
	 *            ：要匹配的字符串
	 * @param mate
	 *            ：匹配方式，支持 endsWith , startsWith , contains 三种，默认 contains
	 * @return
	 */
	public static String fileExist(String folderPath, String regulation,
			String mate) {
		File dir = new File(folderPath);
		File file[] = dir.listFiles();
		if (file != null) {
			for (int i = 0; i < file.length; i++) {
				if ("endsWith".equals(mate)) {
					if (file[i].getName().toLowerCase()
							.endsWith(regulation.toLowerCase())) {
						return file[i].getName();
					}
				} else if ("startsWith".equals(mate)) {
					if (file[i].getName().toLowerCase()
							.startsWith(regulation.toLowerCase())) {
						return file[i].getName();
					}
				} else {
					if (file[i].getName().toLowerCase()
							.contains(regulation.toLowerCase())) {
						return file[i].getName();
					}
				}
			}
		}
		return "";
	}

	/**
	 * 检索文件夹下符合规则的文件名
	 * 
	 * @param folderPath
	 *            ：要检索的目标文件夹
	 * @param regulation
	 *            ：要匹配的字符串
	 * @param mate
	 *            ：匹配方式，支持 endWith , startWith , contains 三种，默认 contains
	 * @return
	 */
	public static List<String> fileSearch(String folderPath, String regulation,
			String mate) {
		File dir = new File(folderPath);
		File file[] = dir.listFiles();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < file.length; i++) {
			if ("endWith".equals(mate)) {
				if (file[i].getName().endsWith(regulation)) {
					list.add(file[i].getName());
				}
			} else if ("startWith".equals(mate)) {
				if (file[i].getName().startsWith(regulation)) {
					list.add(file[i].getName());
				}
			} else {
				if (file[i].getName().contains(regulation)) {
					list.add(file[i].getName());
				}
			}
		}
		return list;
	}

	/**
	 * 遍历文件夹，返回其下所有文件列表（不包含子文件夹）
	 * 
	 * @param folderPath
	 * @return
	 */
	public static HashSet<String> getFiles(String folderPath) {
		if (!folderPath.endsWith("/")) {
			folderPath = folderPath + "/";
		}
		File dir = new File(folderPath);
		File file[] = dir.listFiles();
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < file.length; i++) {
			if (new File(folderPath + file[i].getName()).isFile()) {
				set.add(file[i].getName());
			}
		}
		return set;
	}

	/**
	 * 检验该路径是否存在：适用于文件和文件夹
	 * 
	 * @param path
	 * @return： true，存在；false，不存在
	 */
	public static boolean checkPath(String path) {
		return new File(path).exists();
	}
}