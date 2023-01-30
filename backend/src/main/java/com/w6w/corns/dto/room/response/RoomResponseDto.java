package com.w6w.corns.dto.room.response;

import com.w6w.corns.domain.room.Room;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
@ApiModel(value="쫑알룸 응답 정보")
public class RoomResponseDto {

    private int roomNo;

    private String title;

    private int time;

    private int maxMember;

    private int hostUserId;

    private String sessionId;

    @Builder
    public RoomResponseDto(int roomNo, String title, int time, int maxMember, int hostUserId, String sessionId) {
        this.roomNo = roomNo;
        this.title =title;
        this.time = time;
        this.maxMember = maxMember;
        this.hostUserId = hostUserId;
        this.sessionId = sessionId;
    }

}
