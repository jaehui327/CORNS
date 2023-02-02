package com.w6w.corns.service.growth;

import com.w6w.corns.domain.explog.ExpLog;
import com.w6w.corns.domain.explog.ExpLogRepository;
import com.w6w.corns.domain.loginlog.LoginLogRepository;
import com.w6w.corns.domain.user.User;
import com.w6w.corns.domain.user.UserRepository;
import com.w6w.corns.dto.explog.ExpLogRequestDto;
import com.w6w.corns.dto.explog.ExpLogResponseDto;
import com.w6w.corns.dto.level.LevelDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GrowthServiceImpl implements GrowthService {

    private final UserRepository userRepository;
    private final ExpLogRepository expLogRepository;
    private final LoginLogRepository loginLogRepository;

    public int calExpPercentile(int userId) throws Exception{

        //user에서 usercd 탈퇴, 정지를 제외한 나머지 사람들의 총 exp 순위
        int cntAll = userRepository.countAll();

        int rank = userRepository.rankByExp(userId);

        return rank / cntAll * 100;
    }

    public LevelDto getUserLevel(int userId) throws Exception{

        User user = userRepository.findByUserId(userId);
        return LevelDto.fromEntity(user.getLevel());
    }

    public List<ExpLogResponseDto> getExpLogList(int userId, Pageable pageable, String baseTime) throws Exception{
        //baseTime -> LocalDate 타입으로
        LocalDateTime localDateTime = LocalDateTime.parse(baseTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("localDateTime = " + localDateTime);

        Slice<ExpLog> slice = expLogRepository.findByUserIdAndRegTmLessThan(userId, pageable, localDateTime);
        System.out.println("slice = " + slice.getContent());

        List<ExpLogResponseDto> list = new ArrayList<>();
        for(ExpLog expLog : slice.getContent())
            list.add(ExpLogResponseDto.fromEntity(expLog));

        return list;
    }

    //경험치 부여
    @Override
    @Transactional
    public void giveExp(ExpLogRequestDto expLogRequestDto){

        expLogRepository.save(expLogRequestDto.toEntity());
    }

    //출석률 반환
    @Override
    public int calAttendanceRate(int userId) throws Exception {

        int countPerMonth = loginLogRepository.findByRegTmAndUserIdPerMonth(userId);
        System.out.println(countPerMonth);

        double rate = (double)countPerMonth / 30;
        return (int)(rate * 100);
    }
}
