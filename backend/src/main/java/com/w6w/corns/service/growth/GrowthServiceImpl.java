package com.w6w.corns.service.growth;

import com.w6w.corns.domain.level.LevelRepository;
import com.w6w.corns.domain.user.User;
import com.w6w.corns.domain.user.UserRepository;
import com.w6w.corns.dto.level.LevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrowthServiceImpl implements GrowthService {

    private final LevelRepository levelRepository;
    private final UserRepository userRepository;

    public int calExpPercentile(int userId) throws Exception{

        //user에서 usercd 탈퇴, 정지를 제외한 나머지 사람들의 총 exp 순위
        int cntAll = userRepository.countAll();

        int rank = userRepository.rankByExp(userId);

        return rank / cntAll * 100;
    }

    public LevelDto getUserLevel(int userId) throws Exception{

//        User user = userRepository.findByUserId(userId);
//        return LevelDto.fromEntity(user.getLevel());
        return null;
    }
}
