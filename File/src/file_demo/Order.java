package file_demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import domain.Student;

public class Order {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) throws IOException {
		/**
		 * 需求：将学生的总分成绩按高到低进行排序
		 */
		Set<Student> set = new TreeSet<Student>(Collections.reverseOrder());
		set.add(new Student("傅雷", 60, 80, 76));
		set.add(new Student("赵强", 80, 80, 76));
		set.add(new Student("雷颖", 90, 80, 86));
		set.add(new Student("黄渤", 70, 90, 96));
		set.add(new Student("陈景容", 98, 93, 91));

		// 将排序后的内容进行永久性的存储
		File dir = new File("tempfile");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir, "Grade.txt");
		writeToFile(set, file);
	}

	private static void writeToFile(Set<Student> set, File file) throws IOException {
//		想要存储，需要用到输出流
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(file);
			for (Student student : set) {
				String info = student.getName()+"\t"+student.getSum()+LINE_SEPARATOR;
				fos.write(info.getBytes());
			}
		}finally{
			if(!(fos == null)){
				try{
					fos.close();
				}catch(IOException e){
					throw new RuntimeException("资源关闭异常");
				}
			}
			
		}
	}
}
