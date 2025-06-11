package io.gffd94.backend.exceptions

import java.lang.RuntimeException

class MemberNotFoundException : RuntimeException("조건에 맞는 회원을 찾을 수 없습니다.")
