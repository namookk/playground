package com.playground.user.repository;

import com.playground.user.payload.domain.CTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CTableRepository extends JpaRepository<CTable, Long> {

}
