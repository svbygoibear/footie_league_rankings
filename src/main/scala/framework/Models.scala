package framework

/**
  * Created by Simone van Buuren on 2016/10/28.
  */

// Defines some of the base models/ case classes used to define incoming data.

case class Team(name: String, goals: Int)
case class Match(teams: Seq[Team])
