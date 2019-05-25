package com.sullivankw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
		
	@Bean
	public File getFile() throws Exception {
		
		try {
			File file = new File("file.json");
			if (!file.createNewFile()) {
				LOGGER.debug("file.json already exists");	
			}		
			return file;
		} catch (Exception e) {
			LOGGER.error("Program must exit. File not found and unable to create new one.");
		}
		throw new Exception();
	}	
		
	@Bean
	 public BufferedWriter getDoAppendBufferWriter() throws Exception {
		try {
			return new BufferedWriter(new FileWriter(getFile(), true));
		} catch (IOException e)  {
		LOGGER.error("Must exit program. Unable to create appened BufferedWriter.");
		}
		throw new Exception();
	}
	
	@Bean
	 public BufferedReader getBufferedReader() throws Exception {
		try {
			return new BufferedReader(new FileReader(getFile()));
		} catch (FileNotFoundException e) {
			LOGGER.error("Must exit program. Unable to create appened BufferedReader.");
		} 
		throw new Exception();
	}
	
}
