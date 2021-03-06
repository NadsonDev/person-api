package com.nadson.personapi.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper=false)
public class PersonDTO extends RepresentationModel<PersonDTO>{

	private Long id;
	
	@NotEmpty(message="The first name is mandatory")
	@Size(min=2, max=100)
	private String firstName;
	
	@NotEmpty(message="The last name is mandatory")
	@Size(min=2, max=100)
	private String lastName;
	
	@NotEmpty(message="The cpf is mandatory")
	@CPF(message="Invalid cpf")
	private String cpf;
	
	private String birthDate;
	
	@NotEmpty(message="The phones list is mandatory")
	@Valid
	private List<PhoneDTO> phones;
}
