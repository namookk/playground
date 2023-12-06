package com.playground.user.service;

import com.playground.user.payload.domain.Member;
import com.playground.user.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final int max = 60000;

    @Transactional
    public void init() {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            members.add(Member.newInstance());
        }

        System.out.println(members.size() + "-------------------");
        memberRepository.saveAll(members);
    }

    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findByEmail("nwhwang@hanssem.com");
    }
}
