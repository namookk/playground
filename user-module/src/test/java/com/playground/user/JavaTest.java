package com.playground.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

enum YN {
  Y,
  N;
}

public class JavaTest {

  @Test
  void test() {
    List<Object> emptyList = Collections.emptyList();
    List list = Collections.EMPTY_LIST;
    System.out.println(CollectionUtils.isEmpty(emptyList));
    System.out.println(CollectionUtils.isEmpty(list));
    System.out.println(ObjectUtils.isEmpty(emptyList));
    System.out.println(ObjectUtils.isEmpty(list));
    System.out.println("gg");
  }

  @Test
  void sortTest() {
    List<TestDto> testDtos = new ArrayList<>();
    testDtos.add(new TestDto("1", "시공비", "기본"));
    testDtos.add(new TestDto("1", "시공비", "추가"));
    testDtos.add(new TestDto("1", "시공비", "추가"));
    testDtos.add(new TestDto("1", "시공비", "추가"));
    testDtos.add(new TestDto("1", "기타비용", "추가"));
    testDtos.add(new TestDto("1", "기타비용", "추가"));
    testDtos.add(new TestDto("2", "시공비", "기본"));
    testDtos.add(new TestDto("2", "시공비", "추가"));
    testDtos.add(new TestDto("2", "시공비", "추가"));
    testDtos.add(new TestDto("2", "시공비", "추가"));
    testDtos.add(new TestDto("2", "시공비", "추가"));
    testDtos.add(new TestDto("2", "기타비용", "추가"));

    testDtos.stream()
        .sorted(Comparator.comparing(TestDto::getOrdrDdlnNo).reversed()
            .thenComparing(TestDto::getBlcsTypeName).reversed()
            .thenComparing(TestDto::getCostSprnNm))
        .forEach(System.out::println);
  }

  @Test
  void enumTest() {
    YN yn = YN.valueOf(null);
    System.out.println(yn);
  }
}

class TestDto {

  private String ordrDdlnNo;
  private String blcsTypeName;
  private String costSprnNm;

  public TestDto(String ordrDdlnNo, String blcsTypeName, String costSprnNm) {
    this.ordrDdlnNo = ordrDdlnNo;
    this.blcsTypeName = blcsTypeName;
    this.costSprnNm = costSprnNm;
  }

  public String getOrdrDdlnNo() {
    return ordrDdlnNo;
  }

  public String getBlcsTypeName() {
    return blcsTypeName;
  }

  public String getCostSprnNm() {
    return costSprnNm;
  }

  @Override
  public String toString() {
    return "TestDto{" +
        "ordrDdlnNo='" + ordrDdlnNo + '\'' +
        ", blcsTypeName='" + blcsTypeName + '\'' +
        ", costSprnNm='" + costSprnNm + '\'' +
        '}';
  }
}

