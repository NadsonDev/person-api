package com.nadson.personapi.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.dto.response.MessageResponseDTO;
import com.nadson.personapi.entity.Person;
import com.nadson.personapi.repository.PersonRepository;
import static com.nadson.personapi.utils.PersonUtils.*;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	@InjectMocks
	private PersonService personService;
	
	@Test
	void testGivenPersonDTOThenReturnCreatedMessage() {
		PersonDTO personDTO = createFakeDTO();
		Person expectedCreatedPerson = createFakeEntity();
		
		when(personRepository.save(expectedCreatedPerson)).thenReturn(expectedCreatedPerson);
		
		MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedCreatedPerson.getId()); 
		MessageResponseDTO successMessage = personService.createPerson(personDTO);
		
		assertEquals(expectedSuccessMessage, successMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO
				.builder()
				.message("Created person with ID " + id)
				.build();
	}
}
