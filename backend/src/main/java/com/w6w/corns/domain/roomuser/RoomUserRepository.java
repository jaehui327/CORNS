package com.w6w.corns.domain.roomuser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomUserRepository extends JpaRepository<RoomUser, Integer> {

    List<RoomUser> findByUserIdAndRoomUserCd(int userId, int roomUserCd);
}
