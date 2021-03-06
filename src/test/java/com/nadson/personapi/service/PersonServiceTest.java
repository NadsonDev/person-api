package com.nadson.personapi.service;

import com.nadson.personapi.dto.mapper.PersonMapper;
import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.dto.response.MessageResponseDTO;
import com.nadson.personapi.entity.Person;
import com.nadson.personapi.exception.PersonNotFoundException;
import com.nadson.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.nadson.personapi.utils.PersonUtils.createFakeDTO;
import static com.nadson.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private PersonMapper personMapper;

	@InjectMocks
	private PersonService personService;

	
	@Test
	void testGivenPersonDTOThenReturnCreatedMessage() {
		PersonDTO personDTO = createFakeDTO();
		Person expectedCreatedPerson = createFakeEntity();

		when(personMapper.toModel(personDTO)).thenReturn(expectedCreatedPerson);
		when(personRepository.save(any(Person.class))).thenReturn(expectedCreatedPerson);

		MessageResponseDTO successMessage = personService.create(personDTO);

		assertEquals("Person successfully created with ID 1", successMessage.getMessage());
	}
	

	@Test
	void testGivenValidPersonIdThenReturnThisPerson() throws PersonNotFoundException {
		PersonDTO expectedPersonDTO = createFakeDTO();
		Person expectedSavedPerson = createFakeEntity();
		expectedPersonDTO.setId(expectedSavedPerson.getId());
				
		when(personRepository.findById(expectedSavedPerson.getId())).thenReturn(Optional.of(expectedSavedPerson));
		when(personMapper.toDTO(expectedSavedPerson)).thenReturn(expectedPersonDTO);
		
		PersonDTO personDTO = personService.findById(expectedSavedPerson.getId());
		
		assertEquals(expectedPersonDTO, personDTO);
		assertEquals(expectedSavedPerson.getId(), personDTO.getId());
		assertEquals(expectedSavedPerson.getFirstName(), personDTO.getFirstName());
	}

	@Test
	void testGivenInvalidPersonIdThenThrowException() {
		var invalidPersonId = 1L;
		when(personRepository.findById(invalidPersonId))
		.thenReturn(Optional.ofNullable(any(Person.class)));

		assertThrows(PersonNotFoundException.class, () -> personService.findById(invalidPersonId));
	}

	/*
	 * TODO: corrigir este teste
	@Test
	void testGivenNoDataThenReturnAllPeopleRegistered() {
		List<Person> expectedRegisteredPeople = Collections.singletonList(createFakeEntity());
		PersonDTO personDTO = createFakeDTO();
		personDTO.setId(1L);
		
		System.out.println("Experado:" + expectedRegisteredPeople + "\n | Criado:" + personDTO);
		when(personRepository.findAll()).thenReturn(expectedRegisteredPeople);
		when(personMapper.toDTO(any(Person.class))).thenReturn(personDTO);

		List<PersonDTO> expectedPeopleDTOList = personService.listAll();
		
		System.out.println(expectedPeopleDTOList.isEmpty());
		System.out.println(expectedPeopleDTOList.get(0) + "/" + personDTO);
		assertFalse(expectedPeopleDTOList.isEmpty());
		assertEquals(expectedPeopleDTOList.get(0).getId(), personDTO.getId());
	}
	*/

	/* 
	 * TODO: corrigir este teste
	@Test
	void testGivenValidPersonIdAndUpdateInfoThenReturnSuccesOnUpdate() throws PersonNotFoundException {
		var updatedPersonId = 2L;

		PersonDTO updatePersonDTORequest = createFakeDTO();
		updatePersonDTORequest.setId(updatedPersonId);
		updatePersonDTORequest.setLastName("Lovelace updated");

		Person expectedPersonToUpdate = createFakeEntity();
		expectedPersonToUpdate.setId(updatedPersonId);

		Person expectedPersonUpdated = createFakeEntity();
		expectedPersonUpdated.setId(updatedPersonId);
		expectedPersonToUpdate.setLastName(updatePersonDTORequest.getLastName());

		when(personRepository.findById(updatedPersonId)).thenReturn(Optional.of(expectedPersonUpdated));
		when(personMapper.toModel(updatePersonDTORequest)).thenReturn(expectedPersonUpdated);
		when(personRepository.save(any(Person.class))).thenReturn(expectedPersonUpdated);

		MessageResponseDTO successMessage = personService.update(updatedPersonId, updatePersonDTORequest);

		assertEquals("Person successfully updated with ID 2", successMessage.getMessage());
	}
	*/

	@Test
	void testGivenInvalidPersonIdAndUpdateInfoThenThrowExceptionOnUpdate() throws PersonNotFoundException {
		var invalidPersonId = 1L;

		PersonDTO updatePersonDTORequest = createFakeDTO();
		updatePersonDTORequest.setId(invalidPersonId);
		updatePersonDTORequest.setLastName("Lovelace updated");

		when(personRepository.findById(invalidPersonId))
		.thenReturn(Optional.ofNullable(any(Person.class)));

		assertThrows(PersonNotFoundException.class, () -> personService.update(invalidPersonId, updatePersonDTORequest));
	}

	@Test
	void testGivenValidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
		var deletedPersonId = 1L;
		Person expectedPersonToDelete = createFakeEntity();

		when(personRepository.findById(deletedPersonId)).thenReturn(Optional.of(expectedPersonToDelete));
		personService.delete(deletedPersonId);

		verify(personRepository, times(1)).deleteById(deletedPersonId);
	}

	@Test
	void testGivenInvalidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
		var invalidPersonId = 1L;

		when(personRepository.findById(invalidPersonId))
		.thenReturn(Optional.ofNullable(any(Person.class)));

		assertThrows(PersonNotFoundException.class, () -> personService.delete(invalidPersonId));
	}
}
