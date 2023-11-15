package com.playground.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

  private final Environment env;

  @GetMapping
  public String getUsers() {
    return "this port is %s".formatted(env.getProperty("local.server.port"));
  }

  @GetMapping("/{userId}")
  public Long getUserInfo(@PathVariable Long userId) {
    return userId;
  }
}
