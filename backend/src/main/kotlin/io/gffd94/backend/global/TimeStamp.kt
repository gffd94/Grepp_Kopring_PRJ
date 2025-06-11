package io.gffd94.backend.global

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass // JPA 엔티티 클래스들이 상속해서 사용할 수 있는 공통 매핑정보를 제공하는 어노테이션
                    // DB 테이블에는 생성하지 않지만 이 클래스의 필드를 테이블 컬럼으로 포함
@EntityListeners(AuditingEntityListener::class) // AOP방식으로 동작하며 생성 및 수정 이벤트 시 가로채서 시간정보를 자동으로 채워줌
abstract class TimeStamp {
    @CreatedDate // 생성되는 시점에 JPA가 데이터를 찍어줌
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate // 수정되는 시점에 JPA가 데이터를 찍어줌
    lateinit var updatedAt: LocalDateTime
}