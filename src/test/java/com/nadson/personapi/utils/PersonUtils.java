package com.nadson.personapi.utils;

import java.time.LocalDate;
import java.util.Collections;

import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.entity.Person;

public class PersonUtils {

	private static final String FIRST_NAME = "Ada";
	private static final String LAST_NAME = "Lovelace";
	private static final String CPF_NUMBER = "123.456.789-09";
	private static final Long PERSON_ID = 1L;
	public static final LocalDate BIRTH_DATE = LocalDate.of(1990, 10, 16);
	
	public static PersonDTO createFakeDTO() {
		return PersonDTO.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate("10-10-2010")
				.phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
				.build();
	}
	
	public static Person createFakeEntity() {
		return Person.builder()
				.id(PERSON_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();
	}
}
