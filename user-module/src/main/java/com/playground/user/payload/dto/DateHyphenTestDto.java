package com.playground.user.payload.dto;

import com.playground.user.payload.annotation.DateHyphen;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateHyphenTestDto {

    @DateHyphen
    private String date;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateHyphenTestDto2 {

        private List<DateHyphenTestDto3> tests;
        private DateHyphenTestDto3 test;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public static class DateHyphenTestDto3 {

        @DateHyphen
        private String date;

        private String date2;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestDateHyphenTest {

        private DateHyphenTestData data;

        @DateHyphen
        private String date;

        private String date2;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateHyphenTestData {

        @DateHyphen
        private String date;

        private String date2;
    }
}
