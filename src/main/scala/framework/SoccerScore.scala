package framework

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Defines what the outcome is for a match - to determine later what the rankings of a team is.
  */

// Defines scoring and comparing scores (their integer values) as well as their outcomes as objects.
sealed class SoccerScore(val points: Int) extends Ordered[SoccerScore] {
  def compare(that: SoccerScore) = points.compare(that.points)
}

object WinScore extends SoccerScore(3)
object DrawScore extends SoccerScore(1)
object LoseScore extends SoccerScore(0)
