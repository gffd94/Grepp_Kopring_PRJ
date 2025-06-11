package io.gffd94.backend.domain

import io.gffd94.backend.dto.MemberUpdateDescription
import io.gffd94.backend.dto.Role
import io.gffd94.backend.global.TimeStamp
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.transaction.annotation.Transactional

@Entity
class Member (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String,
    var email: String,

    @Enumerated(EnumType.STRING)
    var role: Role,

) : TimeStamp() {

    fun update(updateDesc: MemberUpdateDescription) {

        this.name = updateDesc.name
        this.role = updateDesc.role

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member

        if (id != other.id) return false
        if (name != other.name) return false
        if (email != other.email) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + role.hashCode()
        return result
    }
}

