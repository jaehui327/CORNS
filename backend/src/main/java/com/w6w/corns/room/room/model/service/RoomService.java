package com.w6w.corns.room.room.model.service;

import com.w6w.corns.room.room.entity.Room;
import com.w6w.corns.room.room.entity.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room save(Room room) {
        roomRepository.save(room);
        return room;
    }

}
