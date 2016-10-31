package framework

import scala.util.matching.Regex
import scalaz._
import Scalaz._
import scala.language.postfixOps

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Used to interpret lines of a file to generate usable case classes from the data
  */

object FileInterpreter {
  val regex = "([\\w\\s]+[\\D])([\\d]+)".r

  // Generates a match from a line of string
  def createMatch(line: String): Match = {
    val teams = line.split(',') toList

    def loop(teams: List[String], tSeq: Seq[Team]): Match = teams.isEmpty match {
      case true => Match(tSeq)
      case false => loop(teams.tail, tSeq :+ createTeam(teams.head.trim))
    }
    loop(teams, Seq())
  }

  // Creates a team object from a string
  def createTeam(section: String): Team = {
    val regex(name, goals) = section
    Team(name trim, goals.toInt)
  }
}