package com.eventpro.AttendeeService.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.eventpr.SalesService.dto.SaleDTO;
import com.eventpro.AttendeeService.dto.AttendeeDTO;
import com.eventpro.AttendeeService.dto.CheckInDTO;

public interface AttendeeService {

	public void create(final AttendeeDTO attendee);
	
	public AttendeeDTO findById(final Integer id);
	
	public List<AttendeeDTO> findAll(final Boolean overEighteen);

	public void notify(final SaleDTO saleDto);

	public CompletableFuture<Boolean> checkin(CheckInDTO checkin);
}
