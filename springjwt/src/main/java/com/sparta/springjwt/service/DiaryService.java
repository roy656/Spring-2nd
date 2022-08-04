package com.sparta.springjwt.service;

import com.sparta.springjwt.dto.DiaryRequestDto;
import com.sparta.springjwt.model.Diary;
import com.sparta.springjwt.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    @Autowired
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }



    @Transactional
    public Diary creatDiary(DiaryRequestDto requestDto, Long userId) {
        Diary diary = new Diary(requestDto, userId);

        diaryRepository.save(diary);
        return diary;
    }


    @Transactional
    public List<Diary> getUserDiaries(Long userId) {
        return diaryRepository.findAllByUserId(userId);
    }




    // 게시글 업데이트,삭제 관련
//    @Transactional
//    public Long updateDiary(Long id, DiaryRequestDto requestDto) {
//        Diary found = diaryRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
//        );
//        found = updateDiary(requestDto);
//        return id;
//    }


}
