package com.eventpro.AttendeeService.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventpro.AttendeeService.model.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {

	public List<Attendee> findAllByBirthDateAfter(LocalDate limitDate);

	public List<Attendee> findAllByBirthDateBefore(LocalDate limitDate);

}
