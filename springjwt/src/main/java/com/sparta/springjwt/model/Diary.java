package com.sparta.springjwt.model;


import com.sparta.springjwt.dto.DiaryRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Diary extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;


    public Diary(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Diary(DiaryRequestDto requestDto, Long userId) {     // 로그인 한 user 의 게시글 작성시 사용 되는 생성자
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.userId = getUserId();
    }

//    public void updateDiary(DiaryRequestDto requestDto) {
//        this.title = requestDto.getTitle();
//        this.content = requestDto.getContent();
//    }
}