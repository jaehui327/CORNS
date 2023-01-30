package com.w6w.corns.service.room;


import com.w6w.corns.dto.room.request.CreateRoomRequestDto;
import com.w6w.corns.dto.room.response.RoomListResponseDto;
import com.w6w.corns.dto.room.response.RoomResponseDto;
import com.w6w.corns.dto.room.response.RoomUserListResponseDto;

import java.util.List;

public interface RoomService {

    public int save(CreateRoomRequestDto body);

    public boolean isAvailable(int userId);

    public List<RoomListResponseDto> findAll();

    public RoomResponseDto findRoomByRoomNo(int roomNo);

    public List<RoomUserListResponseDto> findRoomUserByRoomNo(int roomNo);

}
