package com.jomolangma.app.thinkingjava.io;

//: io/FreezeAlien.java
// Create a serialized output file.
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class FreezeAlien {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ObjectOutput out = new ObjectOutputStream(
				new FileOutputStream("X.file"));
		Alien quellek = new Alien();
		out.writeObject(quellek);
	}
} // /:~
