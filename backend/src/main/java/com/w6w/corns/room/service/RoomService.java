package com.w6w.corns.room.service;

import com.w6w.corns.room.domain.entity.Room;
import com.w6w.corns.room.domain.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface RoomService {

    public Room save(Room room);
    public List<Room> findAll();
    public Optional<Room> findByRoomNo(int roomNo);

}
