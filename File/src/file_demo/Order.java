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
		 * ���󣺽�ѧ�����ֳܷɼ����ߵ��ͽ�������
		 */
		Set<Student> set = new TreeSet<Student>(Collections.reverseOrder());
		set.add(new Student("����", 60, 80, 76));
		set.add(new Student("��ǿ", 80, 80, 76));
		set.add(new Student("��ӱ", 90, 80, 86));
		set.add(new Student("�Ʋ�", 70, 90, 96));
		set.add(new Student("�¾���", 98, 93, 91));

		// �����������ݽ��������ԵĴ洢
		File dir = new File("tempfile");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir, "Grade.txt");
		writeToFile(set, file);
	}

	private static void writeToFile(Set<Student> set, File file) throws IOException {
//		��Ҫ�洢����Ҫ�õ������
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
					throw new RuntimeException("��Դ�ر��쳣");
				}
			}
			
		}
	}
}
