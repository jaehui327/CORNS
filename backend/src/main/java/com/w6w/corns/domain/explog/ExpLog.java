package com.w6w.corns.domain.explog;

import com.w6w.corns.util.BaseTime;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@DynamicInsert
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Entity
public class ExpLog extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expLogSq;

    private int userId;

    private int gainExp;

    private int expCd;
}
