package com.playground.authserver.service;

import com.playground.authserver.payload.domain.Member;
import com.playground.authserver.payload.dto.MemberDto.SignUpRequest;
import com.playground.authserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member signUp(SignUpRequest request) {
        return memberRepository.save(Member.newInstance(request, passwordEncoder));
    }
}
