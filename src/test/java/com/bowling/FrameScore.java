package com.bowling;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FrameScore {
  private final int id;
  private final int score;
  private final int totalBollScore;
}
