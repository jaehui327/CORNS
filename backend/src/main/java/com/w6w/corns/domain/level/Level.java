package com.w6w.corns.domain.level;

import com.w6w.corns.util.BaseTime;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Level extends BaseTime {

    @Id
    int levelNo;

    int startExp;

    int endExp;

}
