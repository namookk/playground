package com.playground.user.repository;

import static com.playground.user.payload.domain.QMember.member;

import com.playground.user.payload.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> getAllMembers(List<Long> ids) {
        return queryFactory.selectFrom(member)
            .where(member.id.in(ids))
            .fetch();
    }
}
