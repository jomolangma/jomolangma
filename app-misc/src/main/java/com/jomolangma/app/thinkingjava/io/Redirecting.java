package com.jomolangma.app.thinkingjava.io;

//: io/Redirecting.java
// Demonstrates standard I/O redirection.
import java.io.*;

public class Redirecting {
	public static void main(String[] args) throws IOException {
		PrintStream console = System.out;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				"./src/main/java/com/jomolangma/app/thinkingjava/io/Redirecting.java"));
		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream("./src/main/java/com/jomolangma/app/thinkingjava/io/test.out")));
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = br.readLine()) != null)
			System.out.println(s);
		out.close(); // Remember this!
		System.setOut(console);
		System.out.println("aaaaaaaaaaaaaaa");
	}
} // /:~
