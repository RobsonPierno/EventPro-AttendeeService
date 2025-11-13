package com.eventpro.AttendeeService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventpro.AttendeeService.client.AttendeeClient;
import com.eventpro.AttendeeService.dto.AttendeeDTO;
import com.eventpro.AttendeeService.dto.CheckInDTO;
import com.eventpro.AttendeeService.service.AttendeeService;

@RestController
@RequestMapping("/attendee")
public class AttendeeController implements AttendeeClient {
	
	@Autowired
	private AttendeeService service;

	@Override
	public void create(AttendeeDTO attendee) {
		this.service.create(attendee);
	}

	@Override
	public AttendeeDTO findById(Integer id) {
		AttendeeDTO dto = this.service.findById(id);
		return dto;
	}

	@Override
	public List<AttendeeDTO> findAll(Boolean overEighteen) {
		List<AttendeeDTO> dtos = this.service.findAll(overEighteen);
		return dtos;
	}

	@Override
	public void checkin(CheckInDTO checkin) {
		this.service.checkin(checkin);
	}

}
