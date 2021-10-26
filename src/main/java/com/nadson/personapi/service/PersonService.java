package com.nadson.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadson.personapi.dto.mapper.PersonMapper;
import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.dto.response.MessageResponseDTO;
import com.nadson.personapi.entity.Person;
import com.nadson.personapi.exception.PersonNotFoundException;
import com.nadson.personapi.repository.PersonRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

	private PersonRepository personRepository;
	private final PersonMapper personMapper;
	
	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}
	
	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person personToFind = verifyIfExists(id);
		return personMapper.toDTO(personToFind);
	}
	
	public MessageResponseDTO create(PersonDTO personDTO){
		Person personToCreate = personMapper.toModel(personDTO);
		Person createdPerson = personRepository.save(personToCreate);
		return createMessageResponse("Person successfully created with ID ", createdPerson.getId());
	}

	public MessageResponseDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException{
		verifyIfExists(id);
		Person personToUpdate = personMapper.toModel(personDTO);
		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse("Person successfully updated with ID ", updatedPerson.getId());	
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);	
	}
	
	private Person verifyIfExists(Long id) throws PersonNotFoundException{
		return personRepository.findById(id)
				.orElseThrow(()-> new PersonNotFoundException(id));
	}
	
	private MessageResponseDTO createMessageResponse(String message, Long id) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
}
