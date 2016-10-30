/**
  * Created by Simone van Buuren on 2016/10/28.
  * Usage: Main execution/command line application.
  */

object Main extends App {
  println(s"${Console.BLUE}Enter the file name which contains the league results (e.g. input/footie.txt): ${Console.RESET}")
  io.Source.stdin.getLines.foreach{ file =>
    println(s"${Console.BLUE}File to process: $file ${Console.RESET}")
  }
}
