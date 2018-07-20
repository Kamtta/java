package split_file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SplitFile {

	private static final int BUF_SIZE = 1024*60;

	public static void main(String[] args) throws IOException {
		/**
		 * �ָ��ļ�
		 */
		//��ȷ��Ӧ������Դ�Ͳ���Դ
		File data = new File("F:\\Java\\tempfile\\pic.jpg");
		File desfile = new File("F:\\Java\\tempfile\\partsFile");
		splitFile(data,desfile);
	}

	private static void splitFile(File data, File desfile) throws IOException {
		//������Ӧ�Ľ�׳�Ե��ж�
		if(!(data.exists() && data.isFile())){
			throw new RuntimeException("Դ�ļ������ڻ��߲����ļ���ʽ");
		}
		if(!(desfile.exists())){
			desfile.mkdirs();
		}
		//��Դ�ļ�������Ӧ�Ĵ�ȡ
		FileOutputStream fos = null;
		FileInputStream fis = new FileInputStream(data);
		byte[] buf = new byte[BUF_SIZE];
		int len = 0;
		int count = 1;
		while((len = fis.read(buf))!=-1){
			fos = new FileOutputStream(new File(desfile,(count++)+".part"));
			fos.write(buf, 0, len);
			fos.close();
		}
		//�����õ��Ļ�����Ϣ�洢�������ļ���
		fos = new FileOutputStream(new File(desfile,"pic.properties"));
		Properties p = new Properties();
		p.setProperty("filename", "pic.jpg");
		p.setProperty("partsCount", Integer.toString(count));
		//�����Լ������ݽ��������Դ洢
		p.store(fos, "The info Of picture");
		fos.close();
		fis.close();
	}

}
