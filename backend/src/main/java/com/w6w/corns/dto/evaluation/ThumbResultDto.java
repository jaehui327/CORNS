package com.w6w.corns.dto.evaluation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ThumbResultDto {
    private int userId;
    private String nickname;
    private String imgUrl;
    private int thumbCnt;
}
