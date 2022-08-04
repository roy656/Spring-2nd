package com.sparta.springjwt.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass // Entity가 자동으로 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트합니다.
public abstract class Timestamped {
    // abstract 클래스는 직접 객체를 생성하지 못하고 다른곳으로 상속 되어서만 사용되어진다.

    @CreatedDate  // 생성시간
    private LocalDateTime createdAt;    // 시간을 나타내는 자료형 타입

    @LastModifiedDate  // 수정시간
    private LocalDateTime modifiedAt;
}