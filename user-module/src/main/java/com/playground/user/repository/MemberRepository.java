package com.playground.user.repository;

import com.playground.user.payload.domain.Member;
import java.util.List;

public interface MemberRepository extends MemberJpaRepository, MemberRepositoryCustom {

  List<Member> findByEmail(String email);

  List<Member> findByIdInAndEmailIn(List<Long> ids, List<String> emails);
}
