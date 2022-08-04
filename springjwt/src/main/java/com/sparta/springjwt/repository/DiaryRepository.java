package com.sparta.springjwt.repository;

import com.sparta.springjwt.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByOrderByCreatedAtDesc();

    List<Diary> findAllByUserId(Long userId);
}
