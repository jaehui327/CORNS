package com.w6w.corns.dto.room;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="쫑알룸 유저 응답 정보")
public class RoomUserResponseDto {

    private int userId;

    private String imgUrl;

    private String nickname;

    private String connectionId;

    private String recordId;

    private String token;
}
