package com.mantufo.listsapi.mantufo.listsapi;

import com.mantufo.listsapi.mantufo.listsapi.service.ConvertedSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MantufoListsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantufoListsapiApplication.class, args);
	}


	@Autowired
	ConvertedSheetService convertedSheetService;
}
