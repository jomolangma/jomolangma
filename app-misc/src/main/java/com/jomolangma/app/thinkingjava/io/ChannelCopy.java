package com.jomolangma.app.thinkingjava.io;

//: io/ChannelCopy.java
// Copying a file using channels and buffers
// {Args: ChannelCopy.java test.txt}
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
	private static final int BSIZE = 1024;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		FileChannel in = new FileInputStream("./src/main/java/com/jomolangma/app/thinkingjava/io/from.txt").getChannel();
		FileChannel out = new FileOutputStream("./src/main/java/com/jomolangma/app/thinkingjava/io/to.txt").getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
		while (in.read(buffer) != -1) {
			buffer.flip(); // Prepare for writing
			out.write(buffer);
			buffer.clear(); // Prepare for reading
		}

		in.close();
		out.close();
	}
} // /:~
