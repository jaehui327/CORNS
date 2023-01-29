package com.w6w.corns.service.room;

import com.w6w.corns.domain.room.Room;
import com.w6w.corns.domain.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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
