package com.jomolangma.app.thinkingjava.io;

//: io/Logon.java
// Demonstrates the "transient" keyword.
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
public class Logon implements Serializable {
	private Date date = new Date();
	private String username;
	private transient String password;

	public Logon(String name, String pwd) {
		username = name;
		password = pwd;
	}

	public String toString() {
		return "logon info: \n   username: " + username + "\n   date: " + date
				+ "\n   password: " + password;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		Logon a = new Logon("Hulk", "myLittlePony");
		System.out.println("logon a = " + a);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
				"Logon.out"));
		o.writeObject(a);
		o.close();
		TimeUnit.SECONDS.sleep(1); // Delay
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				"Logon.out"));
		System.out.println("Recovering object at " + new Date());
		a = (Logon) in.readObject();
		System.out.println("logon a = " + a);
	}
} /*
 * Output: (Sample) logon a = logon info: username: Hulk date: Sat Nov 19
 * 15:03:26 MST 2005 password: myLittlePony Recovering object at Sat Nov 19
 * 15:03:28 MST 2005 logon a = logon info: username: Hulk date: Sat Nov 19
 * 15:03:26 MST 2005 password: null
 */// :~
