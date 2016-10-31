package framework

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Calculates the score results from the league matches
  */

object ScoreCalc {
  // Even though the models can cater for more than 2 teams per match, this only gets the results for regular matches  team: Team, score: SoccerScore)
  def calcRegularMatchResult(matchInstance: Match): Map[String, Int] = {
    val teams = matchInstance.teams
    teams.head.goals.compareTo(teams(1).goals) match { //performs a comparison between the two goal values
      case res if res < 0 => Map(teams.head.name -> LoseScore.points, teams(1).name -> WinScore.points)
      case res if res == 0 => Map(teams.head.name -> DrawScore.points,teams(1).name -> DrawScore.points)
      case res if res > 0 => Map(teams.head.name -> WinScore.points, teams(1).name -> LoseScore.points)
    }
  }

  // Groups the results from teams with the same results together and calculates their score from the league results
  def groupLeagueResults(leagueResults: Seq[MatchRank]): List[(Int, List[String])] =
    leagueResults.groupBy(matchResult => matchResult.team).toList.map(res => (res._1, res._2.map(_.score).sum)).groupBy(_._2).map(res => (res._1, res._2.map(_._1))).toList

  // Calculates the league results with the current place/position of a team based on their results
  def getLeagueResults(matches: List[Match]): List[TeamResult] = {
    groupLeagueResults(matches.flatMap{singleMatch => calcRegularMatchResult(singleMatch)
      .map{singleRes => MatchRank(singleRes._1, singleRes._2)}})
      .sortBy(_._1).reverse.zipWithIndex.flatMap{ scoreGroup => scoreGroup._1._2
        .map{ teamRes => TeamResult(scoreGroup._2 + 1, teamRes, scoreGroup._1._1)
      }
    }
  }

  // Changed this method to a map as to use fold |+| operator already built in (do not have to write own semigroup with append functionality)
  def getLeagueResults2(matches: Map[String, Int]): List[TeamResult] = {
    val toMatchRanks = matches.map(singleMatch => MatchRank(singleMatch._1, singleMatch._2)).toSeq
    groupLeagueResults(toMatchRanks).sortBy(_._1).reverse.zipWithIndex.flatMap(res => res._1._2.map(team => TeamResult(res._2, team, res._1._1)))
  }
}
