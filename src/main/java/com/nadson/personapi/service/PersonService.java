package com.nadson.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.dto.response.MessageResponseDTO;
import com.nadson.personapi.entity.Person;
import com.nadson.personapi.mapper.PersonMapper;
import com.nadson.personapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	
	public List<PersonDTO> listPeople() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}
	
	
	public MessageResponseDTO createPerson(PersonDTO personDTO){
		Person personToSave = personMapper.toModel(personDTO);
		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Created person with ID " + savedPerson.getId())
				.build();
	}
	
}
