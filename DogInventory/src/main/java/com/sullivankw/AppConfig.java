package com.sullivankw;

import java.io.File;

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
				
}
