package io.gffd94.backend.util

import io.gffd94.backend.domain.Member
import io.gffd94.backend.dto.MemberDescription
import io.gffd94.backend.dto.Role

fun genMember(
    targetName: String,
    targetEmail: String,
    targetRole: Role = Role.BRONZE,
): Member = Member(null, targetName, targetEmail, targetRole)

fun genMemberDesc(
    actualName: String,
    actualEmail: String,
    actualRole: Role = Role.BRONZE,
): MemberDescription = MemberDescription(actualName, actualEmail, actualRole)

fun genMemberList(size: Int):List<Member> {
    val result: MutableList<Member> = mutableListOf()

    for (i in 1..size) {
        genMember("member$i", "member${i}@email.com")
    }

    return result
}

fun genMemberDescList(size: Int): List<MemberDescription> {
    val result: MutableList<MemberDescription> = mutableListOf()

    for (i in 1..size) {
        result.add(genMemberDesc("member$i", "member${i}@email.com", Role.BRONZE))
    }

    return result
}