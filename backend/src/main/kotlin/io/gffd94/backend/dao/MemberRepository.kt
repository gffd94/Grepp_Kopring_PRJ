package io.gffd94.backend.dao

import io.gffd94.backend.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>