package com.ufinity.config;

import java.util.Properties;

/**
 * @author Vei Sheng Ong
 * This Config.class is use to access and read the config.properties file to query the properties
 */
public class Config {
	Properties configFile;

	public Config() {
		configFile = new java.util.Properties();
		try {
			configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//Get the string key which initiated in the config.properties file
	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}
}
