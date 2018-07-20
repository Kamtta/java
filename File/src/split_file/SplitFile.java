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
		 * 分割文件
		 */
		//明确相应的数据源和操作源
		File data = new File("F:\\Java\\tempfile\\pic.jpg");
		File desfile = new File("F:\\Java\\tempfile\\partsFile");
		splitFile(data,desfile);
	}

	private static void splitFile(File data, File desfile) throws IOException {
		//进行相应的健壮性的判断
		if(!(data.exists() && data.isFile())){
			throw new RuntimeException("源文件不存在或者不是文件格式");
		}
		if(!(desfile.exists())){
			desfile.mkdirs();
		}
		//对源文件进行相应的存取
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
		//将所得到的基本信息存储在配置文件中
		fos = new FileOutputStream(new File(desfile,"pic.properties"));
		Properties p = new Properties();
		p.setProperty("filename", "pic.jpg");
		p.setProperty("partsCount", Integer.toString(count));
		//将属性集的内容进行永久性存储
		p.store(fos, "The info Of picture");
		fos.close();
		fis.close();
	}

}
