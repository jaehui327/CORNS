package com.w6w.corns.service.evaluation;

import com.w6w.corns.domain.selfevaluation.SelfEvaluationRepository;
import com.w6w.corns.domain.thumblog.ThumbLogRepository;
import com.w6w.corns.dto.evaluation.SelfEvaluationDto;
import com.w6w.corns.dto.evaluation.ThumbLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    ThumbLogRepository thumbLogRepo;

    @Autowired
    SelfEvaluationRepository selfEvaluationRepo;

    @Override
    public void voteThumbMember(ThumbLogDto thumbLogDto) {
        thumbLogRepo.save(thumbLogDto.toEntity());
    }

    @Override
    public int getThumbResult(int roomNo, int toUserId) {
        return (int)thumbLogRepo.countByRoomNoAndToUserId(roomNo, toUserId);
    }

    @Override
    public void writeSelfEvaluation(SelfEvaluationDto selfEvalDto) {
        selfEvaluationRepo.save(selfEvalDto.toEntity());
    }

}
