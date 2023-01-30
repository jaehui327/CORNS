package com.w6w.corns.service.room;

import com.w6w.corns.domain.room.Room;
import com.w6w.corns.domain.room.RoomRepository;
import com.w6w.corns.domain.roomuser.RoomUser;
import com.w6w.corns.domain.roomuser.RoomUserRepository;
import com.w6w.corns.dto.room.request.CreateRoomRequestDto;
import com.w6w.corns.dto.room.response.RoomListResponseDto;
import com.w6w.corns.dto.room.response.RoomResponseDto;
import com.w6w.corns.util.code.RoomCode;
import com.w6w.corns.util.code.RoomUserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomUserRepository roomUserRepository;

    @Override
    @Transactional
    public int save(CreateRoomRequestDto body) {
        // check room user code
        if (!isAvailable(body.getUserId())) return -1;

        // save room - set hostUserId, roomCd
        Room room = body.getRoom().toEntity();
        room.setHostUserId(body.getUserId());
        room.setRoomCd(RoomCode.ROOM_WAITING.getCode());
        roomRepository.save(room);

        System.out.println(room);
        // save roomUser - set roomNo, userId, roomUserCd
        RoomUser roomUser = body.getRoomUser().toEntity();
        roomUser.setRoomNo(room.getRoomNo());
        roomUser.setUserId(body.getUserId());
        roomUser.setUserCd(RoomUserCode.ROOM_USER_WAITING.getCode());
        roomUserRepository.save(roomUser);

        return 1;
    }

    @Override
    public boolean isAvailable(int userId) {
        if (roomUserRepository.findByUserIdAndRoomUserCd(userId, RoomUserCode.ROOM_USER_WAITING.getCode()).isEmpty() &&
                roomUserRepository.findByUserIdAndRoomUserCd(userId, RoomUserCode.ROOM_USER_CONVERSATION.getCode()).isEmpty()) return true;
        return false;
    }

    public List<RoomListResponseDto> findAll() {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(e -> rooms.add(e));
        return null;
    }

    public RoomResponseDto findByRoomNo(int roomNo) {
        Optional<Room> result = roomRepository.findById(roomNo);
        if (result.isEmpty()) return null;

        Room room = result.get();
        return RoomResponseDto.builder()
                .roomNo(room.getRoomNo())
                .title(room.getTitle())
                .time(room.getTime())
                .maxMember(room.getMaxMember())
                .hostUserId(room.getHostUserId())
                .sessionId(room.getSessionId())
                .build();
    }

}
