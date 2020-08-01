package com.Shopify.CommonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepo {

	public static Properties instance = null;

	private ObjectRepo() {
		// TODO Auto-generated constructor stub
	}

	public static Properties getInstance() throws Exception, IOException {

		if (instance == null) {
			instance = new Properties();
			instance.load(new FileInputStream(new File(".//application.properties")));
		}
		return instance;
	}
}
