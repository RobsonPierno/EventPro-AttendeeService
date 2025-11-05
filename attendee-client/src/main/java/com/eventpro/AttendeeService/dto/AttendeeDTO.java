package com.eventpro.AttendeeService.dto;

import java.time.LocalDate;

public record AttendeeDTO(Integer id, String name, String phone, String email,  LocalDate birthDate) {

}
