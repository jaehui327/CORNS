package com.w6w.corns.service.room;


import com.w6w.corns.domain.room.Room;
import com.w6w.corns.dto.room.request.CreateRoomRequestDto;
import com.w6w.corns.dto.room.response.RoomListResponseDto;
import com.w6w.corns.dto.room.response.RoomResponseDto;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    public int save(CreateRoomRequestDto body);

    public boolean isAvailable(int userId);

    public List<RoomListResponseDto> findAll();

    public RoomResponseDto findByRoomNo(int roomNo);

}
