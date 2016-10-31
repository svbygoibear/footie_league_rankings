package presentation

import framework.{TeamResult}
import scalaz._, Scalaz._

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: prints out the results from the league.
  */

object LogPrinter {
  // Prints out the results in the specified format from the assignment
  def printLeagueResults(allRes: List[TeamResult]) = {
    allRes.foreach(result =>
      println(s"${result.position + 1}. ${result.name}: ${result.finalScore}${if(result.finalScore == 1)"pt" else "pts"}")
    )
  }
}
