package com.w6w.corns.room.service;

import com.w6w.corns.room.domain.entity.Room;
import com.w6w.corns.room.domain.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room save(Room room) {
        roomRepository.save(room);
        return room;
    }

    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(e -> rooms.add(e));
        return rooms;
    }

    public Optional<Room> findByRoomNo(int roomNo) {
        Optional<Room> room = roomRepository.findById(roomNo);
        return room;
    }

}
