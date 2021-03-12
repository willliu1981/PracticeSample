package com.practicesample.main;

import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * 此類別需放在和Sample同一個package,否則將找不到P1~Pn class,因為 Pn 是一個package 的default 的類別
 */
public abstract class Practice {
	private static final String defaultPrefixName = "P";
	private static final int defaultMaxValueOfSufferName = 100;
	private static final String defaultMethod = "main";
	private static final int LoadClassSuccessful = 1;
	private static final int LoadClassNotFound = 2;
	private static final int OtherError = 3;

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
		if (invoke(this.parentpath, fname) == LoadClassNotFound) {
			throw new Exception("未找到可執行的class");
		}
	}

	public void run(int sufname) throws Exception {
		if (invoke(this.parentpath, this.prename + sufname) == LoadClassNotFound) {
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
			int r = invoke(this.parentpath, fname);
			if (r == LoadClassSuccessful || r == OtherError) {
				break;
			} else {
				continue;
			}
		}
	}

	private static int invoke(String parentpath, String fname) {
		int r = 0;
		Path path = Paths.get("src").relativize(FileSystems.getDefault().getPath(parentpath, fname));
		try {
			Class<?> cls = Class.forName(path.toString().replace("\\", "."));
			Method m = cls.getMethod(defaultMethod, String[].class);
			m.invoke(cls.newInstance(), (Object) new String[] {});
			r = LoadClassSuccessful;
		} catch (ClassNotFoundException e) {
			r = LoadClassNotFound;
		} catch (Exception e) {
			e.printStackTrace();
			r = OtherError;
		}
		return r;
	}
}
