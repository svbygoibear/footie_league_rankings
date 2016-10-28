package framework

/**
  * Created by Simone van Buuren on 2016/10/28.
  */

sealed class SoccerScore(val points: Int) extends Ordered[SoccerScore] {
  def compare(that: SoccerScore) = points.compare(that.points)
}

object WinScore extends SoccerScore(3)
object DrawScore extends SoccerScore(1)
object LoseScore extends SoccerScore(0)
