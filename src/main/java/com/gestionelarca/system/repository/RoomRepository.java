package com.gestionelarca.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionelarca.system.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

     public Room findByType(String type);

}
