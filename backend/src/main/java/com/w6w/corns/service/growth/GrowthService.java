package com.w6w.corns.service.growth;

import com.w6w.corns.dto.level.LevelDto;

public interface GrowthService {
    int calExpPercentile(int userId) throws Exception;
    LevelDto getUserLevel(int userId) throws Exception;
}
