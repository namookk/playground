package com.playground.user.payload.domain;

import com.playground.user.payload.enumtype.TableSequenceType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TableSequencePK implements Serializable {

  private int year;
  private TableSequenceType type;
}
