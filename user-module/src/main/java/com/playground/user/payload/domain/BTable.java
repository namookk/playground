package com.playground.user.payload.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@Entity
@Table(name = "b_table")
public class BTable {

  @Id
  @GenericGenerator(name = "idGenerator", type = com.playground.user.payload.domain.IdGenerator.class,
      parameters = {
          @org.hibernate.annotations.Parameter(name = "type", value = "B")
      })
  @GeneratedValue(generator = "idGenerator")
  @Column(name = "b_id")
  private String id;
}
