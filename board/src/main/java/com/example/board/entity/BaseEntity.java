package com.example.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp //생성되었을때 시간
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp //업데이트가 되었을때 시간
    @Column(insertable = false) //인서트할때는 관여 안하게끔
    private LocalDateTime updatedTime;
}