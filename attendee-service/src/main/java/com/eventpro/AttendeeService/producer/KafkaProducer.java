package com.eventpro.AttendeeService.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.eventpr.SalesService.dto.SaleDTO;
import com.eventpro.AttendeeService.dto.CheckInDTO;

@Component
public class KafkaProducer {

	@Autowired
	@Qualifier("kafkaTemplateSales")
	private KafkaTemplate<String, SaleDTO> kafkaTemplateSales;
	
	@Value("${kafka.topic.ticket.sale.done}")
	private String ticketSaleDoneTopic;
	
	public void ticketSaleDone(final SaleDTO saleDto) {
		String key = String.format("AttendeeId:%s|TicketId:%s}", saleDto.attendeeId(), saleDto.ticketId());
		this.kafkaTemplateSales.send(this.ticketSaleDoneTopic, key, saleDto);
	}
	
	@Autowired
	@Qualifier("kafkaTemplateCheckin")
	private KafkaTemplate<String, CheckInDTO> kafkaTemplateCheckin;
	
	@Value("${kafka.topic.participant.checkin}")
	private String participantCheckedInTopic;
	
	public void participatCheckIn(final CheckInDTO checkinDto) {
		String key = String.format("AttendeeId:%s|EventId:%s}", checkinDto.attendeeId(), checkinDto.eventId());
		this.kafkaTemplateCheckin.send(this.participantCheckedInTopic, key, checkinDto);
	}
}
