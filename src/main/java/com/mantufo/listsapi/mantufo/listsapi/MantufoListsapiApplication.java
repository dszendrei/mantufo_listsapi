package com.mantufo.listsapi.mantufo.listsapi;

import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class MantufoListsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantufoListsapiApplication.class, args);
	}


	@Autowired
	ConvertedSheetService convertedSheetService;
}
