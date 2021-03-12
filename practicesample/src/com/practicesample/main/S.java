package com.practicesample.main;


import java.io.PrintStream;

public class S {
	public static O o = new S().new O();

	public class O extends PrintStream {

		public O() {
			super(System.out);
		}

		public void p(Object o) {
			super.print(o);
		}

		public void l(Object s) {
			super.println(s);
		}

		public void pln(Object s) {
			l(s);
		}

		public void l(String s0, Object s) {
			super.println(s0 + s);
		}

		public void pln(String s0, Object s) {
			l(s0, s);
		}

		public void space(Object s) {
			super.print(s + " ");
		}

		public void comma(Object s) {
			super.print(s + " , ");
		}

		public void p(String s0, Object s) {
			super.print(s0 + s);
		}

		public void l() {
			super.println();
		}

		public void pln() {
			l();
		}

		public void f(String format, Object... os) {
			super.format(format, os);
		}

		public void fn(String format, Object... os) {
			super.format(format + "\n", os);
		}

		public void f(String format) {
			super.format(format);
		}

		public void fn(String format) {
			super.format(format + "\n");
		}

		public void foreach(String s, Object[] os) {
			int count = 0;
			S.o.p(s);
			for (Object o : os) {
				S.o.f("%s%s", o, count++ < os.length - 1 ? " �� " : "");
				;
			}
			S.o.l();

		}

		public void foreach(Object[] os) {
			foreach("", os);
		}
	}

}
