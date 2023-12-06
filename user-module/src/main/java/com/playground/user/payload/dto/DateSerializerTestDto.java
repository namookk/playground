package com.playground.user.payload.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.playground.user.payload.annotation.DisplayStaff;
import com.playground.user.payload.support.HyphenFormatSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DateSerializerTestDto {

    @JsonSerialize(using = HyphenFormatSerializer.class)
    private String date;

    @DisplayStaff
    private String createId;

    @DisplayStaff
    private String updateId;

}
