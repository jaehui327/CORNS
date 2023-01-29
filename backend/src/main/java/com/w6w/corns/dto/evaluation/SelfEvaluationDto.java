package com.w6w.corns.dto.evaluation;

import com.w6w.corns.domain.selfevaluation.SelfEvaluation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SelfEvaluationDto {
    private int roomNo;
    private int userId;
    private int score;
    private String description;

    public SelfEvaluation toEntity() {
        return SelfEvaluation.builder()
                .roomNo(roomNo)
                .userId(userId)
                .score(score)
                .description(description)
                .build();
    }
}