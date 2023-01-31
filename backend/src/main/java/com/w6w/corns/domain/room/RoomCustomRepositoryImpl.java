package com.w6w.corns.domain.room;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.w6w.corns.dto.room.response.RoomListResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoomCustomRepositoryImpl implements RoomCustomRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    private QRoom room = QRoom.room;

    public RoomCustomRepositoryImpl(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Slice<Room> searchBySlice(ArrayList<Integer> subjects, int minTime, int maxTime, boolean isAvail, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (!subjects.isEmpty()) {

        }

        List<Room> rooms = jpaQueryFactory.selectFrom(room)
                .where(builder)
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkLastPage(pageable, rooms);
    }

    // no-offset 방식 처리하는 메서드
    private BooleanExpression ltStoreId(Integer roomNo) {
        if (roomNo == null) {
            return null;
        }

        return room.roomNo.lt(roomNo);
    }

    // 무한 스크롤 방식 처리하는 메서드
    private Slice<Room> checkLastPage(Pageable pageable, List<Room> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }

}
