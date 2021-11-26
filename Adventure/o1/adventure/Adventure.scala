package o1.adventure


/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure {

  /** The title of the adventure game. */
  val title = "Bandersnatch"

  val start = new Step(_ => "not shown hopefully", Vector(), start_f)
  def start_f(i: Int): Option[Step] = Some(tutorial)

  val tutorial = new Step(_ => context.descriptions.tutorial, Vector("Understood", "Not really"), tutorial_f)
  def tutorial_f(i: Int): Option[Step] = i match {
    case 1 =>
      context.understood = true
      Some(home)
    case 2 =>
      context.understood = false
      Some(home)
  }

  val home = new Step(_ => context.descriptions.home, Vector("Go to Dipoli", "Go to A-bloc"), home_f)
  def home_f(i: Int): Option[Step] = i match {
    case 1 =>
      context.restaurant = "Dipoli"
      Some(lunch)
    case 2 =>
      context.restaurant = "A-bloc"
      Some(lunch)
  }
  val lunch = new Step(_ => context.descriptions.lunch, Vector("Accept", "Deny"), lunch_f)
  def lunch_f(i: Int): Option[Step] = i match {
    case 1 =>
      goodbyeMessage = context.endings.teamwork
      isOver = true
      None
  }
  /** The character that the player controls in the game. */
  val player = new Player(start)

  var goodbyeMessage = "this ending should not be shown"

  var isOver = false

  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String): String = {
    def printDelimiter(options: Vector[String], upper:Boolean): String = {
      var s = if (upper) "┌" else "└"
      s += "-" * (options(0).length)
      if(options.length > 1) {
        s += (if(upper) "┬" else "┴")
        s += "-" * (options(1).length)
      }
      s += (if(upper) "┐" else "┘")
      s + "\n"
    }
    val action = new Action(command)
    val outcomeReport = action.execute(this.player)

    if(isOver)
      return ""
//    var step =
    var output = outcomeReport._1
    output += "\n"
    val options = outcomeReport._2

    output += printDelimiter(options, true)

    output += "|" + options(0).toUpperCase + "|"
    if(options.length > 1)
      output += options(1).toUpperCase + "|"

    output += "\n"

    output += printDelimiter(options, false)

    output
  }


}

