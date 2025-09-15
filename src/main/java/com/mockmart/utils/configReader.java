package com.mockmart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {

	Properties prop;

	public void loadProperties() {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream fis = new FileInputStream(
						System.getProperty("user.dir") + File.separator + "src" +
		                          File.separator + "main" + File.separator + "resources" +
		                          File.separator + "config.properties");
				prop.load(fis);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load properties file");
			}
		}
	}

	public String getProperty(String key) {

		loadProperties();
		String value = prop.getProperty(key);
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("Property '" + key + "' not found in properties file");
		}
	}

}
