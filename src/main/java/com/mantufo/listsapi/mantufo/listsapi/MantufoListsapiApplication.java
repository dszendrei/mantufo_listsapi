package com.mantufo.listsapi.mantufo.listsapi;

import com.mantufo.listsapi.mantufo.listsapi.Model.Cell;
import com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate;
import com.mantufo.listsapi.mantufo.listsapi.Model.ListOfCells;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class MantufoListsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MantufoListsapiApplication.class, args);
	}


	@Autowired
	ListOfCells listOfCells;

	@Bean
	@Profile("production")
	public CommandLineRunner init() {

		return args -> {
			Coordinate coordinate = new Coordinate(0, 0);
			listOfCells.getListOfCells().add(new Cell(coordinate, "value"));

		};
	}
}
