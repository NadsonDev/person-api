package com.nadson.personapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.dto.response.MessageResponseDTO;
import com.nadson.personapi.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
	
	PersonService personService;
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PersonDTO> listPeople() {
		return personService.listPeople();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
		return personService.createPerson(personDTO);
	}
	
	
}
