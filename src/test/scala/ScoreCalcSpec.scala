import framework._
import org.scalatest.FlatSpec
import framework.ScoreCalc._
import framework.FileInterpreter._

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: FlatSpec test for automated tests.
  */

class ScoreCalcSpec extends FlatSpec {
  // Testing to see if file reading works
  "The match" should "be filled with two teams" in {
    val testInput = "Lions 3, Snakes 3"
    val res = createMatch(testInput)
    assert(res.teams == Seq(Team("Lions", 3), Team("Snakes", 3)))
  }

  // First example from input: Lions 3, Snakes 3
  "Both teams" should "get 1 point when tied in a match" in {
    val testMatch = Match(Seq(Team("Lions", 3), Team("Snake", 3)))
    val res = calcRegularMatchResult(testMatch)
    assert(res("Lions") == DrawScore.points && res("Snake") == DrawScore.points)
  }

  // Second example from input: Tarantulas 1, FC Awesome 0
  "The winner" should "gets 3 points" in {
    val testMatch = Match(Seq(Team("Tarantulas", 1), Team("FC Awesome", 0)))
    val res = calcRegularMatchResult(testMatch)
    assert(res("Tarantulas") == WinScore.points)
  }

  // My own test
  "The loser" should "gets 0 points" in {
    val testMatch = Match(Seq(Team("Tarantulas", 2), Team("FC Awesome", 4)))
    val res = calcRegularMatchResult(testMatch)
    assert(res("Tarantulas") == LoseScore.points)
  }

  // Testing to see if grouping method works
  "The Tarantulas" should "gets 6 points" in {
    val testLeague = Seq(MatchRank("Tarantulas", WinScore.points), MatchRank("Tarantulas", WinScore.points), MatchRank("Tarantulas", LoseScore.points))
    val res = groupLeagueResults(testLeague)
    assert(res.head._1 == 6)
  }

  // Calculate the result of a league
  "The League results" should "show the Lions as winners" in {
    val res1 = Match(Seq(Team("Lions", 3), Team("Snake", 3)))
    val res2 = Match(Seq(Team("Lions", 3), Team("Snake", 1)))
    val res3 = Match(Seq(Team("Lions", 0), Team("Tarantulas", 1)))
    val matches: List[Match] = List(res1, res2, res3)
    val res = getLeagueResults(matches)
    assert(s"Team: ${res.head.name} Score: ${res.head.finalScore} Position: ${res.head.position}" == "Team: Lions Score: 4 Position: 1")
  }
}
