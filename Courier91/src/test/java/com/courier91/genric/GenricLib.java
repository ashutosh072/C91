package com.courier91.genric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Ashutosh sharma
 * @created on 12/07/2018
 * reviewed by Ashutosh sharma
 *
 */
public class GenricLib {

	 static String filepath="./Config.properties";
	 
	
		public static String getValueString(String key) 
		{
			String value = null;
			
			try
			{
			  Properties prop = new Properties();
			  prop.load(new FileInputStream(new File(filepath)));
			  value = prop.getProperty(key);
			}
			
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			return value;
		}
	}

