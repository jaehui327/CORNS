package com.w6w.corns.dto.room.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value="쫑알룸 리스트 응답 정보")
public class RoomListResponseDto {

    List<RoomResponseDto> rooms;
}
