package merge_file;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class Merger_File {

	private static final int BUF_SIZE = 1024;

	public static void main(String[] args) throws IOException {
		/**
		 * ������Ӧ�������ļ���������Ӧ���ļ���Ƭ�ĺϲ�
		 */
		//��ȷ��Ӧ������Դ
		File partsFile = new File("F:\\Java\\tempfile\\partsFile");
		//������Ӧ������Դ�����ļ��ĺϲ�
		mergeFiles(partsFile);
	}

	private static void mergeFiles(File partsFile) throws IOException {
		//��ȡ��Ӧ�������ļ�
		File configfile = getConfigFile(partsFile);
		//��ȡ������Ϣ����
		Properties p  = getProperties(configfile);
		//����Ƭ���кϲ�
		merge(partsFile,p);
	}

	private static void merge(File partsFile, Properties p) throws IOException {
		//��ȡ��Ӧ�������ļ�����Ϣ
		int partsCount = Integer.parseInt(p.getProperty("partsCount"));
		String file_name = p.getProperty("filename");
		//��Ҫ�������ļ�����Ŀ�Ƕ���������Ҫ��������Ҳ�Ƕ��
		List<FileInputStream> list = new ArrayList<FileInputStream>();
		for (int i = 1; i < partsCount; i++) {
			list.add(new FileInputStream(new File (partsFile,i+".part")));
		}
		//����ö�ٵķ�ʽ����������
		Enumeration<FileInputStream> en = Collections.enumeration(list);
		//���ö���������ʽ��ö�ٶ��󴫽�ȥ������Ӧ�ĵ�������صĲ���
		SequenceInputStream sis = new SequenceInputStream(en);
		//��������������ļ��ĺϲ�
		FileOutputStream fos = new FileOutputStream(new File(partsFile,file_name));
		//���建����
		byte[] buf = new byte[BUF_SIZE];
		int len = 0;
		while((len = sis.read(buf))!=-1){
			fos.write(buf, 0, len);
		}
		sis.close();
		fos.close();
	}

	private static Properties getProperties(File configfile) throws IOException {
		//��ȡ�����ļ����󣬽��ж�����
		FileInputStream fis = null;
		Properties p = new Properties();
		try{
			fis = new FileInputStream(configfile);
			p.load(fis);
		}finally {
			try{
				fis.close();
			}catch (IOException e) {
			}
		}
		return p;
	}

	private static File getConfigFile(File partsFile) {
		//���н�׳�Ե��ж�
		if(!(partsFile.exists() && partsFile.isDirectory())){
			throw new RuntimeException("�ļ�������...");
		}
		//��ȡ�ļ�
		File[] dir = partsFile.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".properties");
			}
		});
		if(dir.length != 1){
			throw new RuntimeException("�����ļ������ڻ��ߴ��ڶ��....");
		}
		return dir[0];
	}

}
