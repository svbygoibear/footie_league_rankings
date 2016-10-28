package framework

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Calculates the score results from the league matches
  */

object ScoreCalc {
  //Even though the models can cater for more than 2 teams per match, this only gets the results for regular matches  team: Team, score: SoccerScore)
  def calcRegularMatchResult(matchInstance: Match): Seq[MatchRank] = {
    val teams = matchInstance.teams
    teams.head.goals.compareTo(teams(1).goals) match { //performs a comparison between the two goal values
      case res if res < 0 => Seq(MatchRank(teams head, LoseScore), MatchRank(teams(1), WinScore))
      case res if res == 0 => Seq(MatchRank(teams head, DrawScore),MatchRank(teams(1), DrawScore))
      case res if res > 0 => Seq(MatchRank(teams head, WinScore), MatchRank(teams(1), LoseScore))
    }
  }

  def groupLeagueResults(matchResults: Seq[MatchRank]): List[(Int, Seq[MatchRank])] = {
    matchResults.groupBy(i => i.score.points).toList.sortBy(it => it._2.map(_.team.name))
  }

  def getLeagueResults() = {

  }

}
