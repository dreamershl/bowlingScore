package com.bowling;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BowlingScores {
  private final int firstScore;
  private final int secondScore;
}
