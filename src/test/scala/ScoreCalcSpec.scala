import framework._
import org.scalatest.FlatSpec
import framework.ScoreCalc._

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: FlatSpec test for automated tests.
  */

class ScoreCalcSpec extends FlatSpec {
  // First example from input: Lions 3, Snakes 3
  "Both teams" should "get 1 point when tied in a match" in {
    val testMatch = Match(Seq(Team("Lions", 3), Team("Snake", 3)))
    val res = calcRegularMatchResult(testMatch)
    assert(res("Lions") == DrawScore && res("Snake") == DrawScore)
  }

  // Second example from input: Tarantulas 1, FC Awesome 0
  "The winner" should "gets 3 points" in {
    val testMatch = Match(Seq(Team("Tarantulas", 1), Team("FC Awesome", 0)))
    val res = calcRegularMatchResult(testMatch)
    assert(res("Tarantulas") == WinScore)
  }

  // My own test
  "The loser" should "gets 0 points" in {
    val testMatch = Match(Seq(Team("Tarantulas", 2), Team("FC Awesome", 4)))
    val res = calcRegularMatchResult(testMatch)
    assert(res("Tarantulas") == LoseScore)
  }

  // Testing to see if grouping method works
  "The Tarantulas" should "gets 6 points" in {
    val testLeague = Seq(MatchRank("Tarantulas", WinScore), MatchRank("Tarantulas", WinScore), MatchRank("Tarantulas", LoseScore))
    val res = groupLeagueResults(testLeague)
    assert(res.head._1 == 6)
  }
}
