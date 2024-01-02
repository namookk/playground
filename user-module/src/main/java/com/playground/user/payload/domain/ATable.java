package com.playground.user.payload.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@NoArgsConstructor
@Entity
@Table(name = "a_table")
public class ATable {

  @Id
  @GenericGenerator(name = "idGenerator", type = com.playground.user.payload.domain.IdGenerator.class,
      parameters = {
          @Parameter(name = "type", value = "A")
      })
  @GeneratedValue(generator = "idGenerator")
  @Column(name = "a_id")
  private String id;
}
