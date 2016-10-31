package framework

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Defines different objects used for working with teams and matches
  */

// Defines some of the base models/ case classes used to define incoming data.
case class Team(name: String, goals: Int)
case class Match(teams: Seq[Team])
case class MatchRank(team: String, score: Int)
case class TeamResult(position: Int, name: String, finalScore: Int)
