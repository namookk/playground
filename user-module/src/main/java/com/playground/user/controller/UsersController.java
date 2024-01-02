package com.playground.user.controller;

import com.playground.user.payload.domain.Member;
import com.playground.user.payload.dto.DateSerializerTestDto;
import com.playground.user.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

  private final Environment env;
  private final MemberService memberService;

  @GetMapping("/test-1")
  public List<Member> getUsers() {
    return memberService.getAllMembers();
  }

  @GetMapping("/test-2")
  public List<Member> getUsers2() {
    return memberService.getAllMembers2();
  }

  @GetMapping("/test-3")
  public String getUsers3() {
    memberService.createData();
    return "OK";
  }

  @GetMapping("/{userId}")
  public Long getUserInfo(@PathVariable Long userId) {
    return userId;
  }

  @GetMapping("/test")
  public DateSerializerTestDto getDateJson(DateSerializerTestDto request) {
    return DateSerializerTestDto.builder()
        .date(request.getDate())
        .build();
  }

  @PostMapping("/test")
  public DateSerializerTestDto getDateJson2(@RequestBody DateSerializerTestDto request) {
    return DateSerializerTestDto.builder()
        .date(request.getDate())
        .build();
  }

  @PostMapping("/init")
  public void init() {
    memberService.init();
  }

}
