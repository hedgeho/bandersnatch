package bandersnatch
package ui

import bandersnatch.VarsIO._
import bandersnatch.{Adventure, context}

import java.lang.Thread.sleep
import scala.io.StdIn._

/** The singleton object `AdventureTextUI` represents a fully text-based version of the
  * Adventure game application. The object serves as a possible entry point for the game,
  * and can be run to start up a user interface that operates in the text console.
  *
  * @see [[AdventureGUI]] */
object AdventureTextUI extends App {

  val game = new Adventure
  this.run()


  /** Runs the game. First, a welcome message is printed, then the player gets the chance to
    * play any number of turns until the game is over, and finally a goodbye message is printed. */
  private def run() = {
    playTurn(false)
    while (!this.game.isOver) {
      this.playTurn()
    }
    println("\n" + this.game.goodbyeMessage)
  }


  /** Requests a command from the player, plays a game turn accordingly, and prints out a report of what happened. */
  private def playTurn(read: Boolean = true): Unit = {
    println()
    val command = if (read) readLine() else "1"
    val (readInput, turnOutput) = this.game.playTurn(command)
    if (!game.isOver) {
      turnOutput.chars().forEach(ch => {
        print(ch.toChar)
        if(ch.toChar == ' ')
          sleep(context.typing_delay)

        if(ch.toChar == '\n')
          sleep(300)
      })
      // used only in case of entering username
      if(readInput) {
        val name = readLine()
        set_var("username", name)
        playTurn(false)
      }
    }
  }

  def play() = {
    // play it yourself pls
    val path = "epic_soundtrack.mp3"
  }
}


