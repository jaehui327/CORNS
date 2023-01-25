package com.w6w.corns.room.domain.repository;

import com.w6w.corns.room.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {

}
