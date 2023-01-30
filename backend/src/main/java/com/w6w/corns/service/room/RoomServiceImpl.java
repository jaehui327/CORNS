package com.w6w.corns.service.room;

import com.w6w.corns.domain.room.Room;
import com.w6w.corns.domain.room.RoomRepository;
import com.w6w.corns.domain.roomuser.RoomUser;
import com.w6w.corns.domain.roomuser.RoomUserRepository;
import com.w6w.corns.domain.user.UserRepository;
import com.w6w.corns.dto.room.request.CreateRoomRequestDto;
import com.w6w.corns.dto.room.response.RoomListResponseDto;
import com.w6w.corns.dto.room.response.RoomResponseDto;
import com.w6w.corns.dto.room.response.RoomUserListResponseDto;
import com.w6w.corns.dto.room.response.RoomUserResponseDto;
import com.w6w.corns.service.subject.SubjectService;
import com.w6w.corns.util.code.RoomCode;
import com.w6w.corns.util.code.RoomUserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomUserRepository roomUserRepository;

    private final SubjectService subjectService;

    private final UserRepository userRepository;

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
        // 참가 가능한 상태면 true 반환 - 6000(대기중), 6001(대기중)인 상태가 없을 때
        if (roomUserRepository.findByUserIdAndRoomUserCd(userId).isEmpty()) return true;
        return false;
    }

    public List<RoomListResponseDto> findAll() {
        return roomRepository.findAll().stream()
                .map(m -> RoomListResponseDto.builder()
                        // room
                        .room(RoomResponseDto.builder()
                                .roomNo(m.getRoomNo())
                                .title(m.getTitle())
                                .time(m.getTime())
                                .maxMember(m.getMaxMember())
                                .roomCd(m.getRoomCd())
                                .sessionId(m.getSessionId())
                                .build())
                        // subject - select by subject no
                        .subject(subjectService.findById(m.getSubjectNo()))
                        .build())
                .collect(Collectors.toList());
    }

    public RoomResponseDto findRoomByRoomNo(int roomNo) {
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

    public List<RoomUserListResponseDto> findRoomUserByRoomNo(int roomNo) {
        List<RoomUser> roomUsers = roomUserRepository.findByRoomNo(roomNo);
        if (roomUsers.isEmpty()) return null;

        return roomUsers.stream()
                .map(m -> RoomUserListResponseDto.builder()
                        .roomUser(RoomUserResponseDto.builder()
                                .connectionId(m.getConnectionId())
                                .recordId(m.getRecordId())
                                .token(m.getToken())
                                .build())
                        .user(userRepository.findById(m.getUserId()).get()
                                .toUserResponseBuilder())
                        .build())
                .collect(Collectors.toList());
    }

}
