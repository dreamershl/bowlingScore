package com.bowling;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

@ScenarioScoped
public class BowlingScoreSteps {
  private final Match match = new Match();
  private static final Map<Integer, Match> labelledMatchMap = new HashMap<>();

  private transient List<Integer> bollsScoreList;

  @DataTableType
  public static BowlingScores convertToBollScores(Map<String, String> dataRow) {
    return BowlingScores.builder().firstScore(Integer.parseInt(dataRow.get("boll1")))
        .secondScore(Integer.parseInt(dataRow.get("boll2")))
        .build();
  }

  @DataTableType
  public static FrameScore convertToFrameScores(Map<String, String> dataRow) {
    return FrameScore.builder().id(Integer.parseInt(dataRow.get("frame id")))
        .score(Integer.parseInt(dataRow.get("score")))
        .totalBollScore(Integer.parseInt(dataRow.get("total boll score")))
        .build();
  }

  @When("this frame bolls scores are {string}")
  public void thisFrameBollsScoresAre(String bollScores) {
    bollsScoreList = Arrays.stream(bollScores.split(",")).map(Integer::parseInt).toList();
  }

  @Then("this frame score is {int} and match score is {int}")
  public void thisFrameScoreIsFrameScore(int frameScore, int matchScore) {
    Frame frame = match.addFrame();
    frame.addScore(bollsScoreList.get(0))
        .addScore(bollsScoreList.get(1));

    Assertions.assertEquals(frameScore, frame.getTotalBollScore());
    Assertions.assertEquals(matchScore, match.getScore());
  }

  @When("^(?:first|next) frame is (?:spare|normal)$")
  public void firstFrameIsSpare(BowlingScores bollScores) {
    match.addFrame().addScore(bollScores.getFirstScore())
        .addScore(bollScores.getSecondScore());
  }

  @Then("the scores should be as the followings")
  public void theScoresShouldBeAsTheFollowings(List<FrameScore> frameScoreList) {
    for (var frameScore : frameScoreList) {
      var frame = match.getFrame(frameScore.getId());

      Assertions.assertNotNull(frame);
      Assertions.assertEquals(frameScore.getTotalBollScore(), frame.getTotalBollScore());
      Assertions.assertEquals(frameScore.getScore(), match.getFrameScore(frameScore.getId()));
    }
  }

  @And("this match score is {int}")
  public void thisMatchScoreIs(int score) {
    Assertions.assertEquals(score, match.getScore());
  }

  @Given("start a match as label {int}")
  public void startAMatch(int matchId) {
    labelledMatchMap.computeIfAbsent(matchId, m -> new Match());
  }

  @When("Match {int} frame {int} boll score is {int}")
  public void matchFrameFrameIdBollScoreIsBollScore(int matchId, int frameId, int bollScore) {
    var match = labelledMatchMap.get(matchId);
    var frame = match.getFrame(frameId);
    if (frame == null) {
      frame = match.addFrame();
    }

    frame.addScore(bollScore);
  }

  @Then("Match {int} frame {int} total boll score is {int}")
  public void matchFrameFrameIdTotalBollScoreIsFrameTotalBollScoreAndScoreIsFrameScore(
      int matchId, int frameId, int frameBollScore) {
    var match = labelledMatchMap.get(matchId);
    var frame = match.getFrame(frameId);

    Assertions.assertNotNull(frame);
    Assertions.assertEquals(frameBollScore, frame.getTotalBollScore());
  }

  @And("Match {int} frame {int} score is {int}")
  public void matchFrameFrameIdScoreIsFrameScore(int matchId, int frameId, int frameScore) {
    var match = labelledMatchMap.get(matchId);
    Assertions.assertEquals(frameScore, match.getFrameScore(frameId));
  }

  @And("Match {int} score is {int}")
  public void matchScoreIsMatchScore(int matchId, int matchScore) {
    var match = labelledMatchMap.get(matchId);
    Assertions.assertEquals(matchScore, match.getScore());
  }
}
