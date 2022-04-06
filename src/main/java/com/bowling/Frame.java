package com.bowling;

import java.util.Arrays;

public class Frame {
  enum BOWLING {
    FIRST,
    SECOND
  }

  private final int[] scoreAry = new int[] {-1, -1};

  public Frame addScore(int bollScore) {
    if (scoreAry[0] == -1) {
      scoreAry[0] = bollScore;
    } else if (scoreAry[1] == -1) {
      scoreAry[1] = bollScore;
    }
    return this;
  }

  public int getBollScore(BOWLING bowling) {
    var result = scoreAry[bowling.ordinal()];
    if (result == -1) {
      result = 0;
    }

    return result;
  }

  public int getTotalBollScore() {
    return Arrays.stream(scoreAry).filter(v -> v != -1).sum();
  }
}
