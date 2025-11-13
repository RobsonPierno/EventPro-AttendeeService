package com.eventpro.AttendeeService.dto;

import java.time.LocalDateTime;

public record CheckInDTO(Integer eventId, Integer attendeeId, LocalDateTime date) {

}
