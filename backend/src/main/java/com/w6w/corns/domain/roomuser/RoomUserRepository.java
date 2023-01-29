package com.w6w.corns.domain.roomuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomUserRepository extends JpaRepository<RoomUser, Integer>, CustomRoomUserRepository {
}
