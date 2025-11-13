package com.eventpro.AttendeeService.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventpr.SalesService.dto.SaleDTO;
import com.eventpro.AttendeeService.dto.AttendeeDTO;
import com.eventpro.AttendeeService.dto.CheckInDTO;
import com.eventpro.AttendeeService.model.Attendee;
import com.eventpro.AttendeeService.producer.KafkaProducer;
import com.eventpro.AttendeeService.repository.AttendeeRepository;
import com.eventpro.AttendeeService.service.AttendeeService;
import com.eventpro.AttendeeService.utils.AttendeeMapper;

@Service
public class AttendeeServiceImpl implements AttendeeService {
	
	private static final Logger log = LogManager.getLogger(AttendeeServiceImpl.class);
	
	@Autowired
	private AttendeeRepository repository;
	
	@Autowired
	private AttendeeMapper mapper;
	
	@Autowired
	private KafkaProducer kafkaProducer;

	@Override
	public void create(final AttendeeDTO dto) {
		log.info("create({})", dto);
		
		Attendee at = this.mapper.toEntity(dto);
		
		this.repository.save(at);
	}

	@Override
	public AttendeeDTO findById(final Integer id) {
		log.info("findById({})", id);
		
		AttendeeDTO dto = this.repository.findById(id)
											.map(e -> this.mapper.toDTO(e))
											.orElseThrow(RuntimeException::new);
		
		return dto;
	}

	@Override
	public List<AttendeeDTO> findAll(final Boolean overEighteen) {
		log.info("findAll({})", overEighteen);
		
		List<AttendeeDTO> dtos;
		Function<Attendee, AttendeeDTO> fromEntityToDto = e -> this.mapper.toDTO(e);
		
		if (overEighteen != null) {
			LocalDate limitDate = LocalDate.now().minusYears(18);
			
			List<Attendee> attendees = overEighteen 
					? this.repository.findAllByBirthDateBefore(limitDate.plusDays(1)) 
					: this.repository.findAllByBirthDateAfter(limitDate);
			
			dtos = attendees.stream().map(fromEntityToDto).toList();
			return dtos;
		}
		
		dtos = this.repository.findAll().stream().map(fromEntityToDto).toList();
		return dtos;
	}

	@Override
	public void notify(SaleDTO saleDto) {
		log.info("notify({})", saleDto);
		
		Attendee att = this.repository.findById(saleDto.attendeeId()).orElseThrow(RuntimeException::new);
		
		log.info("Notification sent to {}, email {}!", att.getName(), att.getEmail());
		
		this.kafkaProducer.ticketSaleDone(saleDto);
	}

	@Override
	public void checkin(CheckInDTO checkin) {
		log.info("checkin({})", checkin);
		
		this.kafkaProducer.participatCheckIn(checkin);
	}

}
