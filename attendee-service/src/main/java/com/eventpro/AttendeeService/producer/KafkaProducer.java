package com.eventpro.AttendeeService.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.eventpr.SalesService.dto.SaleDTO;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, SaleDTO> kafkaTemplate;
	
	@Value("${kafka.topic.ticket.sale.done}")
	private String ticketSaleDoneTopic;
	
	public void ticketSaleDone(final SaleDTO saleDto) {
		String key = String.format("AttendeeId:%s|TicketId:%s}", saleDto.attendeeId(), saleDto.ticketId());
		this.kafkaTemplate.send(this.ticketSaleDoneTopic, key, saleDto);
	}
}
