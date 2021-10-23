package com.nadson.personapi.utils;

import com.nadson.personapi.dto.request.PhoneDTO;
import com.nadson.personapi.entity.Phone;
import com.nadson.personapi.enums.PhoneType;

public class PhoneUtils {

	private static final String PHONE_NUMBER = "(71)90000-0000";
	private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
	private static final long PHONE_ID = 1L;
	
	public static PhoneDTO createFakeDTO() {
		return PhoneDTO.builder()
				.number(PHONE_NUMBER)
				.type(PHONE_TYPE)
				.build();
	}
	
	public static Phone createFakeEntity() {
		return Phone.builder()
				.id(PHONE_ID)
				.number(PHONE_NUMBER)
				.type(PHONE_TYPE)
				.build();
	}
}
