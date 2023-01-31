package com.w6w.corns.domain.room;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;

public interface RoomCustomRepository {

    Slice<Room> searchBySlice(ArrayList<Integer> subjects, int minTime, int maxTime, boolean isAvail, Pageable pageable);

}
