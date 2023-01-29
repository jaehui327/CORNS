package com.w6w.corns.service.evaluation;

import com.w6w.corns.dto.evaluation.SelfEvaluationDto;
import com.w6w.corns.dto.evaluation.ThumbLogDto;

public interface EvaluationService {

    void voteThumbMember(ThumbLogDto thumbLogDto);

    int getThumbResult(int roomNo, int toUserId);

    void writeSelfEvaluation(SelfEvaluationDto selfEvalDto);

}
