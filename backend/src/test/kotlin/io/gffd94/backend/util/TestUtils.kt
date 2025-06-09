package io.gffd94.backend.util

import io.gffd94.backend.domain.Member
import io.gffd94.backend.dto.Role

fun genMember(
    targetName: String,
    targetEmail: String
): Member = Member(null, targetName, targetEmail, Role.BRONZE)
