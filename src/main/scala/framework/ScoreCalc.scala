package framework

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Calculates the score results from the league matches
  */

object ScoreCalc {
  // Even though the models can cater for more than 2 teams per match, this only gets the results for regular matches  team: Team, score: SoccerScore)
  def calcRegularMatchResult(matchInstance: Match): Map[String, SoccerScore] = {
    val teams = matchInstance.teams
    teams.head.goals.compareTo(teams(1).goals) match { //performs a comparison between the two goal values
      case res if res < 0 => Map(teams.head.name -> LoseScore, teams(1).name -> WinScore)
      case res if res == 0 => Map(teams.head.name -> DrawScore,teams(1).name -> DrawScore)
      case res if res > 0 => Map(teams.head.name -> WinScore, teams(1).name -> LoseScore)
    }
  }

  // Groups the results from a team together and calculates their score from the league results
  def groupLeagueResults(leagueResults: Seq[MatchRank]): List[(String, Int)] =
    leagueResults.groupBy(matchResult => matchResult.team).toList.map(res => (res._1, res._2.map(_.score.points).sum))


  def getLeagueResults(matches: List[Match]): List[TeamResult] = {
    val test = matches.flatMap { singleMatch =>
      calcRegularMatchResult(singleMatch).map { singleRes =>
        MatchRank(singleRes._1, singleRes._2)
      }
    }
    test
    null
  }
}
