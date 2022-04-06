Feature: Calculate the bowling score

  @Isolated
    @NormalMatch
  Scenario Outline: knock down less then 10
    Given start a match as label 1
    When Match 1 frame <frame id> boll score is <boll score>
    Then Match 1 frame <frame id> total boll score is <frame total boll score>
    And Match 1 frame <frame id> score is <frame score>
    And Match 1 score is <match score>
    Examples:
      | frame id | boll score | frame total boll score | frame score | match score |
      | 1        | 1          | 1                      | 1           | 1           |
      | 1        | 2          | 3                      | 3           | 3           |
      | 2        | 3          | 3                      | 3           | 6           |
      | 2        | 6          | 9                      | 9           | 12          |
      | 3        | 0          | 0                      | 0           | 12          |
      | 3        | 0          | 0                      | 0           | 12          |

  @SpareNormalMatch
  Scenario: spare frame followed by normal frame
    When first frame is spare
      | boll1 | boll2 |
      | 0     | 10    |
    Then the scores should be as the followings
      | frame id | total boll score | score |
      | 1        | 10               | 0     |
    And this match score is 0

    When next frame is normal
      | boll1 | boll2 |
      | 1     | 8     |
    Then the scores should be as the followings
      | frame id | total boll score | score |
      | 1        | 10               | 11    |
      | 2        | 9                | 9     |
    And this match score is 20



















