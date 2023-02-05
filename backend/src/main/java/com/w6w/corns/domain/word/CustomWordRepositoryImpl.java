package com.w6w.corns.domain.word;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.transaction.Transactional;

@Transactional
public class CustomWordRepositoryImpl implements CustomWordRepository {

    @Override
    public Slice<Word> searchBySlice(String baseTime, int wordStatus, Pageable pageable) {
        return null;
    }

}
