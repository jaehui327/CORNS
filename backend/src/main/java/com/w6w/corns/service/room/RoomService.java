package com.w6w.corns.service.room;


import com.w6w.corns.domain.room.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    public Room save(Room room);

    public List<Room> findAll();

    public Optional<Room> findByRoomNo(int roomNo);

}
