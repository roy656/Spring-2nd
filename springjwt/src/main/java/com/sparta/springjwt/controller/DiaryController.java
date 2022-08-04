package com.sparta.springjwt.controller;


import com.sparta.springjwt.dto.DiaryRequestDto;
import com.sparta.springjwt.model.Diary;
import com.sparta.springjwt.repository.DiaryRepository;
import com.sparta.springjwt.security.UserDetailsImpl;
import com.sparta.springjwt.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class DiaryController {

    private final DiaryService diaryService;
    private final DiaryRepository diaryRepository;

    @Autowired
    public DiaryController(DiaryService diaryService, DiaryRepository diaryRepository){
        this.diaryService = diaryService;
        this.diaryRepository = diaryRepository;
    }

    @GetMapping("/diaries")             // 비로그인 전체 게시글 조회
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/user/diaries")             // 로그인 된 user 가 작성한 게시글 조회
    public List<Diary> getUserDiaries(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        return diaryService.getUserDiaries(userId);
    }

    @PostMapping("/diaries")            // 로그인 된 user 의 게시글 작성.
    public Diary creatDiary(@RequestBody DiaryRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();
        Diary diary = diaryService.creatDiary(requestDto, userId);
        return diary;
    }


        // 게시글 업데이트,삭제 API
//    @DeleteMapping("/diaries/delete")
//    public Long deleteDiary(Long id) {
//        diaryService.deleteDiary(id);
//        return id;
//    }
//
//    @PutMapping("/diaries/update/{id}")
//    public Long updateDiary(@PathVariable Long id, DiaryRequestDto requestDto) {
//        diaryService.updateDiary(id,requestDto);
//        return id;
//    }
}
