package com.w6w.corns.dto.room;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="쫑알룸 응답 정보")
public class RoomResponseDto {

    private int roomNo;

    private String title;

    private int time;

    private int maxMember;

    private int hostUserId;

    private String sessionId;
}
