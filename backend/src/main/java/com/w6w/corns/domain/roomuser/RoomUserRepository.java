package com.w6w.corns.domain.roomuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomUserRepository extends JpaRepository<RoomUser, Integer> {

    @Query(value = "SELECT ru FROM RoomUser ru WHERE ru.userId = :userId and ru.roomUserCd <= 6001")
    List<RoomUser> findByUserIdAndRoomUserCd(int userId);

    List<RoomUser> findByRoomNo(int roomNo);

    List<RoomUser> findByRoomNoAndRoomUserCd(int roomNo, int roomUserCd);

    void deleteByUserIdAndRoomNo(int userId, int roomNo);

    RoomUser findByUserIdAndRoomNo(int userId, int roomNo);
}
