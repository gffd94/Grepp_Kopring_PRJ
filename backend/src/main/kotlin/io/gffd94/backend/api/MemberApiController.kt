package io.gffd94.backend.api

import io.gffd94.backend.app.MemberService
import io.gffd94.backend.dto.GeneralResponses
import io.gffd94.backend.dto.MemberDescription
import io.gffd94.backend.dto.MemberView
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin("*")
@RestController
@RequestMapping("/api/members")
class MemberApiController(
    private val service: MemberService
) {

    @GetMapping
    fun getMemberViews(): GeneralResponses<List<MemberView>> {
        return GeneralResponses(
            data = service.getAllDescView(),
            message = "회원 목록을 정상적으로 조회했습니다!"
        )
    }

    // CRUD
    @PostMapping
    fun saveMember(
        @Valid @RequestBody saveRequest: MemberDescription
    ): GeneralResponses<MemberDescription> {

        return GeneralResponses(
            data = service.save(saveRequest),
            message = "회원이 성공적으로 등록되었습니다."
        )
    }

    @GetMapping("/{email}")
    fun getMemberDescByEmail(
        @PathVariable email: String
    ): GeneralResponses<MemberDescription> {
        return GeneralResponses(
            message = "회원이 성공적으로 조회했습니다.",
            data = service.getDescByEmail(email)
        )
    }

}