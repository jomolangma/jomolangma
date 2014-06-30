package com.jomolangma.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GenerateXmlStrFromTemplate {

	private BufferedReader reader;

	public String getXmlStr(File file) {
		StringBuffer xmlStr = new StringBuffer();

		try {
			FileReader fr = new FileReader(file);
			reader = new BufferedReader(fr);
			String str;
			
			str = reader.readLine();
			while (str != null) {
				xmlStr.append(str);
				str = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xmlStr.toString();
	}

	public static void main(String[] args) {
		GenerateXmlStrFromTemplate parse = new GenerateXmlStrFromTemplate();
		File file = new File(
				"D:/Java/workspace/Jomolangma/app-misc/src/main/resources/SPECIMEN_COLLECT_LIST_INFO_V1.00.xml");
		
		String xmlStr = parse.getXmlStr(file);
		System.out.println(xmlStr);
		
		
	}
}
