package io.gffd94.backend.app

import io.gffd94.backend.dao.MemberRepository
import io.gffd94.backend.dto.MemberDescription
import io.gffd94.backend.dto.Role
import io.gffd94.backend.util.genMember
import io.gffd94.backend.util.genMemberDesc
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MemberServiceUnitTest {

    val repository = mockk<MemberRepository>()
    val service = MemberService(repository)

    @Test
    fun `적절한 데이터가 들어온다면 저장된 후 MemberDescription을 반환한다`() {

        val actualName = "member1"
        val actualEmail = "member1@email.com"
        val actualRole = Role.BRONZE

        val memberDesc = genMemberDesc(actualName, actualEmail, actualRole)
        val member = genMember(actualName, actualEmail, actualRole)

        every { repository.save(member) } returns member

        val expectedMemberDesc = service.save(memberDesc)

        expectedMemberDesc.name shouldBe actualName
        expectedMemberDesc.email shouldBe actualEmail
        expectedMemberDesc.role shouldBe actualRole

    }

    @Test
    fun `회원이 저장되어있고 findByEmail을 호출하면 회원을 정상적으로 조회한다`() {

        val actualName = "member1"
        val actualEmail = "member1@email.com"
        val actualRole = Role.BRONZE

        val expected = genMember(actualName, actualEmail, actualRole)

        every { repository.findByEmail(actualEmail) } returns expected

        val actual = service.findByEmail(actualEmail)

        actual.name shouldBe actualName
        actual.email shouldBe actualEmail
        actual.role shouldBe actualRole

    }

    @Test
    fun `없는 회원의 이메일로 findByEmail을 호출하면 NoSuchElementException이 발생할 것이다`() {

        val unvailableEmail = "UNAVAILABLE_EMAIL"

        every { repository.findByEmail(unvailableEmail) } returns null

        val actual = assertThrows<NoSuchElementException> {service.findByEmail(unvailableEmail)}

        verify(exactly = 1) { repository.findByEmail(unvailableEmail) }
        actual.message shouldBe "해당 회원은 존재하지 않습니다."

    }

    @Test
    fun `회원이 저장되어있고 getDescByEmail을 호출하면 MemberDescription을 정상적으로 반환한다`() {

        val targetEmail = "member1@email.com"
        val expected = genMember("member1", targetEmail)

        every { repository.findByEmail(targetEmail) } returns expected

        val actual = service.getDescByEmail(targetEmail)

        verify(exactly = 1) { repository.findByEmail(targetEmail) }

        actual.name shouldBe expected.name
        actual.email shouldBe expected.email
        actual.role shouldBe expected.role

    }

    @Test
    fun `잘못된 이메일로 getDescByEmail을 호출하면 NoSuchElementException 발생한다`() {



    }
}

// #1 BDD
class MemberServiceUnitTestWithKoTest : BehaviorSpec({

    val repository = mockk<MemberRepository>()
    val service = MemberService(repository)

    // Given when Then

    Given("적절한 데이터가 주어지고") {
        val actualName = "member1"
        val actualEmail = "member1@email.com"
        val actualRole = Role.BRONZE

        val memberDesc = genMemberDesc(actualName, actualEmail, actualRole)
        val member = genMember(actualName, actualEmail, actualRole)

        every {
            repository.save(member)
        } returns member

        When("memberService를 호출하면") {
            val expected = service.save(memberDesc)

            Then("repository의 save가 한 번 호출될 것이다."){
                verify(exactly = 1) {
                    repository.save(member)
                }
            }

            Then("반환된 MemberDescription의 값은 주어진 값과 동일할 것이다"){
                expected.name shouldBe actualName
                expected.email shouldBe actualEmail
                expected.role shouldBe actualRole
            }

        }
    }

})


// #2 함수 기반 스타일
class MemberServiceUnitTestWithFunSpec : FunSpec({

    val repository = mockk<MemberRepository>()
    val service = MemberService(repository)

    test("적절한 데이터가 들어온다면 저장된 후 MemberDescription을 반환한다.") {
        val actualName = "member1"
        val actualEmail = "member1@email.com"
        val actualRole = Role.BRONZE

        val memberDesc = genMemberDesc(actualName, actualEmail, actualRole)
        val member = genMember(actualName, actualEmail, actualRole)

        every { repository.save(member) } returns member

        val expectedMemberDesc = service.save(memberDesc)

        expectedMemberDesc.name shouldBe actualName
        expectedMemberDesc.email shouldBe actualEmail
        expectedMemberDesc.role shouldBe actualRole
    }

})


// #3 StringSpec
class MemberServiceUnitTestWithStringSpec : StringSpec({

    val repository = mockk<MemberRepository>()
    val service = MemberService(repository)

    "적잘한 데이터가 들어온다면 저장된 후 MemberDescription을 반환한다." {
        val actualName = "member1"
        val actualEmail = "member1@email.com"
        val actualRole = Role.BRONZE

        val memberDesc = genMemberDesc(actualName, actualEmail, actualRole)
        val member = genMember(actualName, actualEmail, actualRole)

        every { repository.save(member) } returns member

        val expectedMemberDesc = service.save(memberDesc)

        expectedMemberDesc.name shouldBe actualName
        expectedMemberDesc.email shouldBe actualEmail
        expectedMemberDesc.role shouldBe actualRole
    }

})


// #4 DescribeSpec
class MemberServiceUnitTestWithDescribeSpec : DescribeSpec({
    val repository = mockk<MemberRepository>()
    val service = MemberService(repository)

    describe("MemberService의 save메서드는 ") {
        context("적절한 데이터가 주어진다면 ") {
            val actualName = "member1"
            val actualEmail = "member1@email.com"
            val actualRole = Role.BRONZE

            val memberDesc = genMemberDesc(actualName, actualEmail, actualRole)
            val member = genMember(actualName, actualEmail, actualRole)

            every { repository.save(member) } returns member

           it("데이터를 저장하고 입력한 정보와 동이랗ㄴ MEmberDescription을 반환할 것이다.") {
               verify (exactly = 1){
                   repository.save(member)
               }

               val expectedMemberDesc = service.save(memberDesc)

               expectedMemberDesc.name shouldBe actualName
               expectedMemberDesc.email shouldBe actualEmail
               expectedMemberDesc.role shouldBe actualRole
           }
        }
    }
})


