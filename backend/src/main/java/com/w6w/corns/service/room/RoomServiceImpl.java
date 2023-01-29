package com.w6w.corns.service.room;

import com.w6w.corns.domain.roomuser.RoomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomUserRepository roomUserRepo;

    @Override
    public List<Integer> getUserList(int roomNo) {
        return roomUserRepo.findUserByRoomNo(roomNo);
    }
}
