package com.w6w.corns.service.growth;

import com.w6w.corns.dto.explog.ExpLogResponseDto;
import com.w6w.corns.dto.level.LevelDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface GrowthService {
    //경험치 상위 백분위 계산
    int calExpPercentile(int userId) throws Exception;
    //유저의 레벨 정보 반환
    LevelDto getUserLevel(int userId) throws Exception;
    //유저의 경험치 목록 반환
    List<ExpLogResponseDto> getExpLogList(int userId, Pageable pageable) throws Exception;
}
