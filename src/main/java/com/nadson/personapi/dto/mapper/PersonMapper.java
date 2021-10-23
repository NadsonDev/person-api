package com.nadson.personapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.nadson.personapi.dto.request.PersonDTO;
import com.nadson.personapi.entity.Person;

@Mapper
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
	
	//TODO retirar essa brincadeira daqui, substituir pelo formato padrao
	@Mapping(target="birthDate", source="birthDate", dateFormat = "dd-MM-yyyy")
	Person toModel(PersonDTO personDTO);
	
	PersonDTO toDTO(Person person);
}
