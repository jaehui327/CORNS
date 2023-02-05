package com.w6w.corns.domain.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public class CustomUserRepositoryImpl implements CustomUserRepository{

    private final JPAQueryFactory jpaQueryFactory;
    public CustomUserRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
    @Override
    public Slice<User> findByFilterRegTmLessThanEqual(Pageable pageable, LocalDateTime baseTime, String filter, String keyword) {

        JPAQuery<User> jpaQuery = jpaQueryFactory
                .selectFrom(QUser.user)
                .where(condition(filter, keyword))
//                .where(regTm.lt(baseTime))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1);

        List<User> list = jpaQuery.fetch();

        boolean hasNext = false;
        if(list.size() > pageable.getPageSize()){
            list.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(list, pageable, hasNext);
    }

    private BooleanExpression condition(String filter, String keyword){
        if(filter.equals("id")){
            int userId = Integer.parseInt(keyword);
             return QUser.user.userId.eq(userId);
        }
        return QUser.user.nickname.eq(keyword);
    }
}
