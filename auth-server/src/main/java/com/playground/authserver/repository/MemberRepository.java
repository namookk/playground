package com.playground.authserver.repository;

import com.playground.authserver.payload.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
