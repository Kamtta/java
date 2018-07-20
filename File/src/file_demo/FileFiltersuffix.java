package file_demo;

import java.io.File;
import java.io.FileFilter;

public class FileFiltersuffix implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		return pathname.getName().endsWith(".java");
	}

	public FileFiltersuffix() {
		super();
		// TODO Auto-generated constructor stub
	}

}
