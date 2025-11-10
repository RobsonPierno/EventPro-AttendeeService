package com.eventpro.AttendeeService.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.eventpr.SalesService.dto.SaleDTO;
import com.eventpro.AttendeeService.service.AttendeeService;

@Component
public class KafkaConsumer {

	@Autowired
	private AttendeeService attendeeService;
	
	@KafkaListener(
		topics = "${kafka.topic.payment.success}",
		containerFactory = "ticketSaleDoneKafkaListener"
	)
	public void ticketSaleDone(final SaleDTO saleDto) {
		this.attendeeService.notify(saleDto);
	}
}
