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

//  // Groups the results from a team together and calculates their score from the league results
  //  def groupLeagueResults(leagueResults: Seq[MatchRank]): List[(String, Int)] =
  //    leagueResults.groupBy(matchResult => matchResult.team).toList.map(res => (res._1, res._2.map(_.score.points).sum))

   //Groups the results from teams with the same results together and calculates their score from the league results
    def groupLeagueResults(leagueResults: Seq[MatchRank]): List[(Int, List[String])] =
      leagueResults.groupBy(matchResult => matchResult.team).toList.map(res => (res._1, res._2.map(_.score.points).sum)).groupBy(_._2).map(res => (res._1, res._2.map(_._1))).toList

  // Calculates the league results with the current place/position of a team based on their results
  def getLeagueResults(matches: List[Match]): List[TeamResult] = {
    val matchResults = matches.flatMap { singleMatch =>
      calcRegularMatchResult(singleMatch).map { singleRes =>
        MatchRank(singleRes._1, singleRes._2)
      }
    }
    val groupedResults = groupLeagueResults(matchResults).sortBy(_._1)
    val fin = groupedResults.zipWithIndex.flatMap{ scoreGroup =>
      scoreGroup._1._2.map{ teamRes =>
        TeamResult(scoreGroup._2, teamRes, scoreGroup._1._1)
      }
    }
    fin
  }
}
