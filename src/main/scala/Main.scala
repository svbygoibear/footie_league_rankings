import java.nio.file.{Files, Paths}

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, IOResult}
import akka.stream.scaladsl.{Sink => sSink, _}
import akka.util.ByteString

import scalaz._
import Scalaz._
import framework.ScoreCalc._
import framework.FileInterpreter._
import framework.{Match, SoccerScore, Team}
import presentation.LogPrinter._

import scala.concurrent.Future

/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Main execution/command line application.
  */

object Main extends App {
  // Implicits needed for Akka streams
  implicit val system = ActorSystem("FootieLeagueRankings")
  implicit val materializer = ActorMaterializer()

  println(s"${Console.BLUE}Enter the file name which contains the league results (e.g. input/footie.txt): ${Console.RESET}")
  try {
    io.Source.stdin.getLines.foreach { filePath =>
      println(s"""${Console.BLUE}File to process: ${filePath.repr} ${Console.RESET}""")
      val file = Paths.get(filePath)
      Files.exists(file) match {
        case false => println(s"${Console.RED}The specified file does not exist.${Console.RESET}") // file not found
        case true => // file found
          // Akka streams used as this works well for big files - it does not load the whole file into memory
          FileIO.fromPath(file)
            .via(Framing.delimiter(ByteString(System.lineSeparator), maximumFrameLength = 1000, allowTruncation = true))
            .map(_.utf8String)
            .map(_ |> createMatch |> calcRegularMatchResult)
            .fold(Map[String, Int]())(_ |+| _)
            .runWith(sSink.foreach(_ |> getLeagueResults2 |> printLeagueResults))
      }
    }
  } catch {
    case t: Throwable => println(s"${Console.RED}An error occurred while processing file: $t ${Console.RESET}")
  }
}
