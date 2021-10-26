package com.nadson.personapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.dto.response.MessageResponseDTO;
import com.nadson.personapi.exception.PersonNotFoundException;
import com.nadson.personapi.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/people")
@Api(value="Person REST API")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
	
	PersonService personService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value="Returns a People list")
	public List<PersonDTO> listAllPeople() throws PersonNotFoundException {
		List<PersonDTO> people = personService.listAll();
		for(PersonDTO personDTO : people) {
			long id = personDTO.getId();
			personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		}
		System.out.println(people);
		return people; 
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Returns a Person specific")
	@ResponseStatus(HttpStatus.OK)
	public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
		PersonDTO personDTO = personService.findById(id);
		personDTO.add(linkTo(methodOn(PersonController.class).listAllPeople()).withRel("People list"));
		return personDTO;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value="Create a Person")
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
		return personService.create(personDTO);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value="Update a Person")
	public MessageResponseDTO updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
		return personService.update(id, personDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="Delete a Person")
	public void deletePerson(@PathVariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}
}
