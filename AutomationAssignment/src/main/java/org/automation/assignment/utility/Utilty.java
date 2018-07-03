package org.automation.assignment.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilty {

	public static String fetchProprtyValue(String key) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("./src/main/resources/config/config.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		return properties.getProperty(key).toString();
	}
	
	public static String fetchElementLocatorValue(String key) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("./src/main/resources/config/Elements.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);
		return properties.getProperty(key).toString();
	}
}
