package io.gffd94.backend.dao

import io.gffd94.backend.domain.Member
import io.gffd94.backend.dto.Role
import io.gffd94.backend.util.genMember
import io.gffd94.backend.util.genMemberList
import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

//val logger = LoggerFactory.getLogger(MemberRepositoryTests::class.java)
private val log = KotlinLogging.logger {}

//@DataJpaTest // Srping Data JPA에 관련되 테스트들만 빈으로 띄워서 테스트 할 수 있다
@SpringBootTest
class MemberRepositoryTests @Autowired constructor(
    var repository: MemberRepository
) {

    // 필드 주입 , 위에는 생성자 주입
//    @Autowired
//    lateinit var repository: MemberRepository

    @Test
    fun `repository 주입 테스트`() {

        log.info{repository}
        assertThat(repository).isNotNull

    }

    @Test
    fun `회원을 생성해서 저장하면 id와 생성날짜, 수정날짜가 자동으로 등록된다`() {

        val targetName = "member1"
        val targetEmail = "member1@email.com"

        val member = genMember(targetName, targetEmail)

        val saved: Member = repository.save(member)

        assertThat(saved.createdAt).isNotNull
        assertThat(saved.updatedAt).isNotNull
        assertThat(saved.id).isNotNull

        val now = LocalDateTime.now()

        assertThat(saved.createdAt).isBefore(now)
        assertThat(saved.updatedAt).isBefore(now)

        log.info { "saved.id: ${saved.id}" }
        log.info { "saved.createdAt: ${saved.createdAt}" }
        log.info { "saved.updatedAt: ${saved.updatedAt}" }


    }

    @Test
    fun `회원 저장 후 findAllMemberView 메서드를 통해서 리스트를 불러오면 MemberView 타입으로 불러올 수 있다`() {

        val size = 10

        val memberList = genMemberList(size)
        repository.saveAll(memberList)

        val descList = repository.findAllMemberView()

        assertThat(descList.size).isEqualTo(size)
        Assertions.assertThat(descList.size).isEqualTo(size)

        descList.forEachIndexed { index, actual ->
            val expected = memberList[index]

            Assertions.assertThat(actual.name).isEqualTo(expected.name)
            Assertions.assertThat(actual.email).isEqualTo(expected.email)
            Assertions.assertThat(actual.role).isEqualTo(expected.role)
        }


    }


}




