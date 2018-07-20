package domain;

public class Student implements Comparable<Student> {
	private String name;
	private int english, math, chinese;
	private int sum;

	public Student() {
		super();
	}

	public Student(String name, int english, int math, int chinese) {
		super();
		this.name = name;
		this.english = english;
		this.math = math;
		this.chinese = chinese;
		this.sum = math + english + chinese;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sum != other.sum)
			return false;
		return true;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public int getSum() {
		return english + math + chinese;
	}

	@Override
	public int compareTo(Student o) {
		int temp = this.sum - o.sum;
		return temp == 0 ? this.name.compareTo(o.name) : temp;
	}
}
