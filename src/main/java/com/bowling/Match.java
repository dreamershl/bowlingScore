package com.bowling;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class Match {
  private final List<Frame> frameList = new ArrayList<>();

  public Frame addFrame() {
    var frame = new Frame();
    frameList.add(frame);
    return frame;
  }

  public int getScore() {
    int result = 0;
    for (int index = 0; index < frameList.size(); index++) {
      var frame = frameList.get(index);
      result += getFrameScore(frame, index);
    }

    return result;
  }

  private int getFrameScore(Frame frame, int frameIndex) {

    int result = frame.getTotalBollScore();
    int nextFrameIndex = frameIndex + 1;

    if (frame.getTotalBollScore() == 10) {
      result = 0;
      Frame nextFrame = null;

      if (nextFrameIndex < frameList.size()) {
        nextFrame = frameList.get(nextFrameIndex);
      }

      if (!(nextFrame == null || nextFrame.getTotalBollScore() == 10)) {
        result += frame.getTotalBollScore() + nextFrame.getBollScore(Frame.BOWLING.FIRST);
      }
    }

    return result;
  }

  public int getFrameScore(int frameId) {
    int result = 0;
    var frame = getFrame(frameId);
    if (frame != null) {
      result = getFrameScore(frame, frameId - 1);
    }

    return result;
  }

  public @Nullable
  Frame getFrame(int id) {
    Frame frame = null;
    if (id <= frameList.size()) {
      frame = frameList.get(id - 1);
    }

    return frame;
  }
}
