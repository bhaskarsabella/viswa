package com.jpmc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {

	private String FILE_NAME;
	
	FileUtil(String fileName) {
		this.FILE_NAME = fileName;
	}
	
	public String getProperty(String key) {
		String result = "";
		InputStream inputStream = null;
		try {
			Properties prop = new Properties();
			String propFileName = this.FILE_NAME;

			inputStream = getClass().getClassLoader().getResourceAsStream(
					propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			result = prop.getProperty(key);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
