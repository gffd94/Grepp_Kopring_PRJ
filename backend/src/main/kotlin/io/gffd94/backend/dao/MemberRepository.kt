package io.gffd94.backend.dao

import io.gffd94.backend.domain.Member
import io.gffd94.backend.dto.MemberView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?

    @Query("SELECT m.name as name, m.email as email, m.role as role FROM Member m")
    fun findAllMemberView(): List<MemberView>

}