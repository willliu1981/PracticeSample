package com.practicesample.main;

import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class Practice {
	private static final String defaultPrefixName = "P";
	private static final int defaultMaxValueOfSufferName = 100;
	private static final String defaultMethod = "main";
	private String parentpath;
	private String prename = defaultPrefixName;
	private int maxValueOfSufferName = defaultMaxValueOfSufferName;

	// Constructor
	private Practice(String parent) throws Exception {
		this.parentpath = parent;
	}

	public static Practice get(String parent) throws Exception {
		return new Practice(parent) {
		};
	}

	public Practice prename(String prename) {
		this.prename = prename;
		return this;
	}

	public Practice sufnameMax(int max) {
		this.maxValueOfSufferName = max;
		return this;
	}

	public void run(String fname) throws Exception {
		if (!invoke(this.parentpath, fname)) {
			throw new Exception("未找到可執行的class");
		}
	}

	public void run(int sufname) throws Exception {
		if (!invoke(this.parentpath, this.prename + sufname)) {
			throw new Exception("未找到可執行的class");
		}
	}

	public void run() throws Exception {
		String fname = null;

		int maxcount = this.maxValueOfSufferName;
		while (true) {
			if (maxcount == 0) {
				throw new Exception("未找到任何可執行的class");
			}
			fname = this.prename + maxcount--;
			if (invoke(this.parentpath, fname)) {
				break;
			} else {
				continue;
			}
		}
	}

	private static boolean invoke(String parentpath, String fname) {
		boolean r = false;
		Path path = Paths.get("src").relativize(FileSystems.getDefault().getPath(parentpath, fname));
		try {
			Class<?> cls = Class.forName(path.toString().replace("\\", "."));
			Method m = cls.getMethod(defaultMethod, String[].class);
			m.invoke(cls.newInstance(), (Object) new String[] {});
			r = true;
		} catch (ClassNotFoundException e) {
			r = false;
		} catch (Exception e) {
			e.printStackTrace();
			r = false;
		}
		return r;
	}
}
