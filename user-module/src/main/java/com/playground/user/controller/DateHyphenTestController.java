package com.playground.user.controller;

import com.playground.user.payload.dto.DateHyphenTestDto.DateHyphenTestDto2;
import com.playground.user.payload.dto.DateHyphenTestDto.DateHyphenTestDto3;
import com.playground.user.payload.dto.DateHyphenTestDto.RequestDateHyphenTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class DateHyphenTestController {

    @GetMapping("/test/1")
    public String test1(@RequestParam String date) {
        return date;
    }

    @GetMapping("/test/2")
    public DateHyphenTestDto3 test2(@ModelAttribute DateHyphenTestDto3 dto) {
        return dto;
    }

    @PostMapping("/test/4")
    public DateHyphenTestDto2 test3(@RequestBody DateHyphenTestDto2 dto) {
        return dto;
    }

    @PostMapping("/test/5")
    public ResponseEntity<RequestDateHyphenTest> test4(@RequestBody RequestDateHyphenTest dto) {
        return ResponseEntity.ok(dto);
    }

}
