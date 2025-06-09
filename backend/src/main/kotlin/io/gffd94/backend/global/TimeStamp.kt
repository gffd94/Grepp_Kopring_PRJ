package io.gffd94.backend.global

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class) // AOP를 적용해서 타임을 적용할 수 있게 함.
abstract class TimeStamp {
    @CreatedDate // 생성되는 시점에 JPA가 데이터를 찍어줌
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate // 수정되는 시점에 JPA가 데이터를 찍어줌
    lateinit var updatedAt: LocalDateTime
}