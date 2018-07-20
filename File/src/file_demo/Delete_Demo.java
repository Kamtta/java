package file_demo;

import java.io.File;

public class Delete_Demo {
	/**
	 * 需求： 删除有内容的目录
	 */
	public static void main(String[] args) {
		// 创建删除文件目标
		File dir = new File("J:\\杂乱");
		// 删除文件
		removeFile(dir);
	}

	private static void removeFile(File dir) {
		// 利用文件数组对目录进行存储
		File[] list = dir.listFiles();
		// 进行健壮性的判断,如果遇到系统级的文件，没有相关的权限是删除不了的，这时可能会出现空指针异常的情况
		if (list != null) {
			for (File file : list) {
				if (file.isDirectory()) {
					removeFile(file);
				} else {
					file.delete();
				}
			}
			dir.delete();
		}
	}

}
