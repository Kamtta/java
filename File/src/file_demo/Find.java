package file_demo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Find {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {
		/**
		 * 需求：获取文件目录上的所有“.java”文件（包括子目录中的文件）,并进行存储
		 */
		File dir = new File("E:\\属于我的世界\\最适合Java入门的视频教程\\源码");
		FileFilter filter = new FileFiltersuffix();
		List<File> list = new ArrayList<File>();
		getFile(dir, filter, list);
		File destFile = new File(dir, "javaList.txt");
		writeToFile(list, destFile);
	}

	private static void writeToFile(List<File> list, File destFile) throws IOException {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(destFile);
			bos = new BufferedOutputStream(fos);
			for (File file : list) {
				String info = file.getAbsolutePath() + LINE_SEPARATOR;
				bos.write(info.getBytes());
				bos.flush();
			}
		} finally {
			if (bos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new RuntimeException("关闭资源失败");
				}
			}
		}
	}

	private static void getFile(File dir, FileFilter filter, List<File> list) {
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getFile(file, filter, list);
			} else {
				if (filter.accept(file)) {
					list.add(file);
				}
			}
		}
	}

}
