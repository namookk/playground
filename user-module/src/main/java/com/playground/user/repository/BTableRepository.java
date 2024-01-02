package com.playground.user.repository;

import com.playground.user.payload.domain.BTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BTableRepository extends JpaRepository<BTable, Long> {

}
