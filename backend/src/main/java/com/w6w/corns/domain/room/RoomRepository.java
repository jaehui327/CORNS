package com.w6w.corns.domain.room;

import com.w6w.corns.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "SELECT r FROM Room r WHERE r.roomCd <= 1001")
    List<Room> findAll();

    Optional<Room> findById(int subjectNo);

}
