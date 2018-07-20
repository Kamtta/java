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
		 * ���󣺻�ȡ�ļ�Ŀ¼�ϵ����С�.java���ļ���������Ŀ¼�е��ļ���,�����д洢
		 */
		File dir = new File("E:\\�����ҵ�����\\���ʺ�Java���ŵ���Ƶ�̳�\\Դ��");
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
					throw new RuntimeException("�ر���Դʧ��");
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
