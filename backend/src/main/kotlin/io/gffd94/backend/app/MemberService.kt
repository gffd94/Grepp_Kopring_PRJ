package io.gffd94.backend.app

import io.gffd94.backend.dao.MemberRepository
import io.gffd94.backend.domain.Member
import io.gffd94.backend.dto.MemberDescription
import io.gffd94.backend.dto.MemberUpdateDescription
import io.gffd94.backend.dto.MemberView
import io.gffd94.backend.exceptions.MemberNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService (
    private val respository : MemberRepository
) {

    //CRUD
    @Transactional
    fun save(desc: MemberDescription): MemberDescription {
        val member = Member(
            name = desc.name,
            email = desc.email,
            role = desc.role
        )

        respository.save(member)
        return desc
    }

    @Transactional(readOnly = true)
    fun findByEmail(email: String): Member {
//        return respository.findByEmail(email) ?: throw NoSuchElementException("해당 회원은 존재하지 않습니다.")
        return respository.findByEmail(email) ?: throw MemberNotFoundException()
    }

    @Transactional(readOnly = true)
    fun getDescByEmail(email: String): MemberDescription {
        val find = findByEmail(email)
        return MemberDescription(
            name = find.name,
            email = find.email,
            role = find.role
        )
    }

    @Transactional
    fun update(email: String, updateDesc: MemberUpdateDescription): MemberUpdateDescription {

        val findMeber = findByEmail(email)
        findMeber.update(updateDesc)

        return MemberUpdateDescription(
            name = findMeber.name,
            role = findMeber.role
        )

    }


    @Transactional
    fun delete(email: String) {

        val findMember = findByEmail(email)
        respository.delete(findMember)

    }

    @Transactional(readOnly = true)
    fun getAllDescView(): List<MemberView> {
        return respository.findAllMemberView()
    }


}
