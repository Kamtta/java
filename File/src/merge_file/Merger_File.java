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
		 * 根据相应的配置文件来进行相应的文件碎片的合并
		 */
		//明确相应的数据源
		File partsFile = new File("F:\\Java\\tempfile\\partsFile");
		//传递相应的数据源进行文件的合并
		mergeFiles(partsFile);
	}

	private static void mergeFiles(File partsFile) throws IOException {
		//获取相应的配置文件
		File configfile = getConfigFile(partsFile);
		//获取配置信息容器
		Properties p  = getProperties(configfile);
		//对碎片进行合并
		merge(partsFile,p);
	}

	private static void merge(File partsFile, Properties p) throws IOException {
		//获取相应的配置文件的信息
		int partsCount = Integer.parseInt(p.getProperty("partsCount"));
		String file_name = p.getProperty("filename");
		//需要操作的文件的数目是多个，因此需要的流对象也是多个
		List<FileInputStream> list = new ArrayList<FileInputStream>();
		for (int i = 1; i < partsCount; i++) {
			list.add(new FileInputStream(new File (partsFile,i+".part")));
		}
		//利用枚举的方式将操作流的
		Enumeration<FileInputStream> en = Collections.enumeration(list);
		//利用队列流的形式将枚举对象传进去进行相应的迭代和相关的操作
		SequenceInputStream sis = new SequenceInputStream(en);
		//利用输出流进行文件的合并
		FileOutputStream fos = new FileOutputStream(new File(partsFile,file_name));
		//定义缓冲区
		byte[] buf = new byte[BUF_SIZE];
		int len = 0;
		while((len = sis.read(buf))!=-1){
			fos.write(buf, 0, len);
		}
		sis.close();
		fos.close();
	}

	private static Properties getProperties(File configfile) throws IOException {
		//获取配置文件对象，进行读操作
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
		//进行健壮性的判断
		if(!(partsFile.exists() && partsFile.isDirectory())){
			throw new RuntimeException("文件不存在...");
		}
		//获取文件
		File[] dir = partsFile.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".properties");
			}
		});
		if(dir.length != 1){
			throw new RuntimeException("配置文件不存在或者存在多个....");
		}
		return dir[0];
	}

}
