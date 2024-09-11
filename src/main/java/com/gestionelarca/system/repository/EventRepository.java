package com.gestionelarca.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.Event;

public interface EventRepository extends  JpaRepository<Event, Long>{

}
