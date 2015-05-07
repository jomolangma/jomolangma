package com.jomolangma.app.thinkingjava.io;

//: io/GZIPcompress.java
// {Args: GZIPcompress.java}
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("./src/main/java/com/jomolangma/app/thinkingjava/io/from.txt"));
		BufferedOutputStream out = new BufferedOutputStream(
				new GZIPOutputStream(new FileOutputStream("./src/main/java/com/jomolangma/app/thinkingjava/io/test.gz")));
		System.out.println("Writing file");
		int c;
		while ((c = in.read()) != -1)
			out.write(c);
		in.close();
		out.close();
		System.out.println("Reading file");
		BufferedReader in2 = new BufferedReader(new InputStreamReader(
				new GZIPInputStream(new FileInputStream("./src/main/java/com/jomolangma/app/thinkingjava/io/test.gz"))));
		String s;
		while ((s = in2.readLine()) != null)
			System.out.println(s);
	}
} /* (Execute to see output) */// :~
