package com.playground.user.payload.domain;

import com.playground.user.payload.enumtype.TableSequenceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@IdClass(value = TableSequencePK.class)
@Table(name = "table_sequence")
public class TableSequence {

  @Id
  @Column(name = "year", nullable = false)
  private int year;

  @Id
  @Enumerated(EnumType.STRING)
  @Column(name = "sequence_type")
  private TableSequenceType type;

  @Builder.Default
  @Column(name = "sequence", nullable = false, precision = 0)
  private Long sequence = 1L;
}
