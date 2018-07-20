package file_demo;

import java.io.File;

public class Delete_Demo {
	/**
	 * ���� ɾ�������ݵ�Ŀ¼
	 */
	public static void main(String[] args) {
		// ����ɾ���ļ�Ŀ��
		File dir = new File("J:\\����");
		// ɾ���ļ�
		removeFile(dir);
	}

	private static void removeFile(File dir) {
		// �����ļ������Ŀ¼���д洢
		File[] list = dir.listFiles();
		// ���н�׳�Ե��ж�,�������ϵͳ�����ļ���û����ص�Ȩ����ɾ�����˵ģ���ʱ���ܻ���ֿ�ָ���쳣�����
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
