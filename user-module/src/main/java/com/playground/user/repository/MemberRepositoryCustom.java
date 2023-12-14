package com.playground.user.repository;

import com.playground.user.payload.domain.Member;
import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> getAllMembers(List<Long> ids);
}
