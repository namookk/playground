package com.playground.user.service;

import com.playground.user.payload.domain.ATable;
import com.playground.user.payload.domain.BTable;
import com.playground.user.payload.domain.CTable;
import com.playground.user.payload.domain.Member;
import com.playground.user.repository.ATableRepository;
import com.playground.user.repository.BTableRepository;
import com.playground.user.repository.CTableRepository;
import com.playground.user.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final ATableRepository aTableRepository;
  private final BTableRepository bTableRepository;
  private final CTableRepository cTableRepository;

  private final int max = 60000;
  private final int MAX_IN_SIZE = 1000;

  @Transactional
  public void init() {
    List<Member> members = new ArrayList<>();
    for (int i = 0; i < max; i++) {
      members.add(Member.newInstance());
    }

    System.out.println(members.size() + "-------------------");
    memberRepository.saveAll(members);
  }

  private List<Long> getIds() {
    List<Long> ids = new ArrayList<>();
    for (long i = 1; i <= 999; i++) {
      ids.add(i);
    }
    return ids;
  }

  @Transactional(readOnly = true)
  public List<Member> getAllMembers() {
    List<Long> ids = getIds();
    List<Member> members = new ArrayList<>();
    for (Long id : ids) {
      members.add(memberRepository.findById(id)
          .orElse(null));
    }

    return members;
  }

  @Transactional(readOnly = true)
  public List<Member> getAllMembers2() {
    return findByInCondition(getIds(), memberRepository::getAllMembers);
  }


  @Transactional
  public void createData() {
    aTableRepository.save(new ATable());
    bTableRepository.save(new BTable());
    cTableRepository.save(new CTable());
  }


  public <T, R> List<R> findByInCondition(List<T> inConditions, Function<List<T>, List<R>> func) {
    List<R> result = new ArrayList<>();
    int idSize = inConditions.size();
    int maxSize = (int) Math.ceil((double) idSize / MAX_IN_SIZE) - 1;

    // in 절에는 1000개 까지만 가능하므로 1000개씩 나눠처 처리
    for (int i = 0; i <= maxSize; i++) {
      int fromIndex = i * MAX_IN_SIZE;
      int toIndex = i == maxSize ? idSize : (i + 1) * MAX_IN_SIZE;
      List<T> ids = inConditions.subList(fromIndex, toIndex);
      result.addAll(func.apply(ids));
    }
    return result;
  }
}
