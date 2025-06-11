package io.gffd94.backend.dto

enum class Role {
    BRONZE, SILVER, GOLD, PLATINUM, DIAMOND
}

/*  엔티티 전체가 아닌 일부 속성만 조회하고 싶을 때 (JPA = projection)
    interface를 사용하면 전체를 DTO로 만들지 않아도 자동으로 선택할 필드만 추출할 수 있다.
    네트워크 향상과 비용절감에도 유리 직접 노출은 피하고 캡슐화된 데이터만 추출
* */
interface MemberView {
    val name: String
    val email: String
    val role: Role
}