package com.w6w.corns.service.room;


import com.w6w.corns.domain.room.Room;
import com.w6w.corns.dto.room.CreateRoomRequestDto;
import com.w6w.corns.dto.room.RoomRequestDto;
import com.w6w.corns.dto.room.RoomUserRequestDto;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    public int save(CreateRoomRequestDto body);

    public boolean isAvailable(int userId);

    public List<Room> findAll();

    public Optional<Room> findByRoomNo(int roomNo);

}
