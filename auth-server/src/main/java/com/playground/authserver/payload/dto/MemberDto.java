package com.playground.authserver.payload.dto;

import lombok.Builder;
import lombok.Value;

public class MemberDto {


    @Value
    @Builder
    public static class SignUpRequest {

        String email;
        String name;
        String password;
    }
}
