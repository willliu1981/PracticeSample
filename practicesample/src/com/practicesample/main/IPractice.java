package com.practicesample.main;


import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface IPractice {
	String defaultfilename = "P";
	int defaultcount = 100;

	static void run(String parent) throws Exception {
		run(parent, defaultcount, defaultfilename);
	}

	static void run(String parent, int count) throws Exception {
		run(parent, count, defaultfilename);
	}

	static void run(String parent, int count, String fname) throws Exception {
		String _fname = fname;
		while (true) {
			if (count == 0) {
				break;
			}
			fname = _fname + count--;
			Path path = Paths.get("src").relativize(FileSystems.getDefault().getPath(parent, fname));
			try {
				Class<?> cls = Class.forName(path.toString().replace("\\", "."));
				Method m = cls.getMethod("main", String[].class);
				m.invoke(cls.newInstance(), (Object) new String[] {});
				break;
			} catch (ClassNotFoundException e) {
				continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
