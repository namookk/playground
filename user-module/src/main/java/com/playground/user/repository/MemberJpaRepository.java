package com.playground.user.repository;

import com.playground.user.payload.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    List<Member> findByIdIn(List<Long> ids);
}
