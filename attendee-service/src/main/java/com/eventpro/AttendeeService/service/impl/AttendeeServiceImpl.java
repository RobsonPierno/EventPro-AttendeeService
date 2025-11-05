package com.eventpro.AttendeeService.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventpro.AttendeeService.dto.AttendeeDTO;
import com.eventpro.AttendeeService.model.Attendee;
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

	@Override
	public void create(final AttendeeDTO dto) {
		log.debug("create({})", dto);
		
		Attendee at = this.mapper.toEntity(dto);
		
		this.repository.save(at);
	}

	@Override
	public AttendeeDTO findById(final Integer id) {
		log.debug("findById({})", id);
		
		AttendeeDTO dto = this.repository.findById(id)
											.map(e -> this.mapper.toDTO(e))
											.orElseThrow(RuntimeException::new);
		
		return dto;
	}

	@Override
	public List<AttendeeDTO> findAll(final Boolean overEighteen) {
		log.debug("findAll({})", overEighteen);
		
		List<AttendeeDTO> dtos;
		Function<Attendee, AttendeeDTO> fromEntityToDto = e -> this.mapper.toDTO(e);
		
		if (overEighteen != null) {
			LocalDate limitDate = LocalDate.now().minusYears(18);
			
			List<Attendee> attendees = overEighteen 
					? this.repository.findAllByBirthDateAfter(limitDate) 
					: this.repository.findAllByBirthDateBefore(limitDate);
			
			dtos = attendees.stream().map(fromEntityToDto).toList();
			return dtos;
		}
		
		dtos = this.repository.findAll().stream().map(fromEntityToDto).toList();
		return dtos;
	}

}
