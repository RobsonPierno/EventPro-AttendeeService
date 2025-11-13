package com.eventpro.AttendeeService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.eventpro.AttendeeService.dto.AttendeeDTO;
import com.eventpro.AttendeeService.dto.CheckInDTO;

@FeignClient(name = "attendee-service", url = "http://attendee:8082", path = "/attendee")
public interface AttendeeClient {

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody final AttendeeDTO attendee);
	
	@GetMapping("/{id}")
	public AttendeeDTO findById(@PathVariable final Integer id);
	
	@GetMapping
	public List<AttendeeDTO> findAll(@RequestParam(required = false) final Boolean overEighteen);
	
	@PostMapping("/checkin")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void checkin(@RequestBody final CheckInDTO checkin);
}
