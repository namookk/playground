package com.playground.user.repository;

import com.playground.user.payload.domain.ATable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATableRepository extends JpaRepository<ATable, Long> {

}
