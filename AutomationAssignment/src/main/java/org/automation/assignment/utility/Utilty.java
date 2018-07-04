package org.automation.assignment.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This is Utility class and used for reading properties files.
 * @author Tushar
 *
 */
public class Utilty {

	public static String fetchProprtyValue(String key) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("./src/main/resources/config/config.properties");
		} catch (FileNotFoundException e) {
			System.err.println("File not found."+e);
			e.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			System.err.println("IO exception occur."+e);
			e.printStackTrace();
		}
		return properties.getProperty(key).toString();
	}
	
	public static String fetchElementLocatorValue(String key) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("./src/main/resources/config/Elements.properties");
		} catch (FileNotFoundException e) {
			System.err.println("File not found."+e);
			e.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			System.err.println("IO exception occur."+e);
			e.printStackTrace();
		}
		return properties.getProperty(key).toString();
	}
}
