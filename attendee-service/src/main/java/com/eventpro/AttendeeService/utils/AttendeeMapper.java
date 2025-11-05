package com.eventpro.AttendeeService.utils;

import org.mapstruct.Mapper;

import com.eventpro.AttendeeService.dto.AttendeeDTO;
import com.eventpro.AttendeeService.model.Attendee;

@Mapper(componentModel = "string")
public interface AttendeeMapper {

	Attendee toEntity(AttendeeDTO dto);
	
	AttendeeDTO toDTO(Attendee entity);
}
