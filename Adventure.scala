package bandersnatch

import bandersnatch.VarsIO.set_var
import bandersnatch.context.{alt_endings_count, bolognese_variant, colin_conversation_count, colin_hint, descriptions, endings, has_gooseslayer, restart_mode}

import java.lang.String.format

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
 * a number of areas that make up the game world. It provides methods for playing the game one
 * turn at a time and for checking the state of the game.
 *
 * N.B. This version of the class has a lot of "hard-coded" information which pertain to a very
 * specific adventure game that involves a small trip through a twisted forest. All newly created
 * instances of class `Adventure` are identical to each other. To create other kinds of adventure
 * games, you will need to modify or replace the source code of this class. */
class Adventure {

  val starting_point = new Step("not shown hopefully", Vector())(_ => {
    if(context.show_tutorial)
      Some(tutorial)
    else
      Some(start)
  })

  val tutorial = new Step(descriptions.tutorial, Vector("Understood", "Not really"))({
    case 1 => // left button
      set_var("show_tutorial", "0")
      Some(start) // None means game over
    case 2 => // right button
      set_var("show_tutorial", "0")
      Some(start)
  })

  val start: Step = new Step(descriptions.start, Vector("There is no time for lunch, the project needs to be finished!", s"${context.username} will write better code with a full stomach!"))({
    case 1 =>
      Some(exercise_session)
    case 2 =>
      Some(canteens)
  })

  val exercise_session = new Step(descriptions.exercise_session, Vector("Take EScooter", "Walk"))({
    case 1 =>
      Some(scooter)
    case 2 =>
      Some(walk)
  })
  val scooter = new Step(descriptions.scooter, Vector("\"For sure!\"", "\"No thanks!\""))({
    case 1 =>
      goodbyeMessage = endings.teamwork
      isOver = true
      None
    case 2 =>
      Some(vision)
  })
  val vision = new Step(descriptions.vision, Vector(s"Yea, ${context.character_name} will get it done!", "No, it's time to give up."))({
    case 1 =>
      goodbyeMessage = endings.eating_goose
      isOver = true
      None
    case 2 =>
      goodbyeMessage = endings.give_up
      isOver = true
      None
  })
  val walk = new Step(descriptions.walk, Vector(s"${context.character_name} shouldn't burn themselves out.", "The project is worth the work!"))({
    case 1 =>
      goodbyeMessage = endings.otacruise
      isOver = true
      None
    case 2 =>
      Some(ta_chat)
  })
  val ta_chat = new Step(descriptions.ta_chat, Vector("The TA is right.", "The vision cannot be ruined!"))({
    case 1 =>
      Some(free_will)
    case 2 =>
      goodbyeMessage = endings.unfinished
      isOver = true
      None
  })
  val free_will = new Step(descriptions.free_will, Vector("What?"))({
    case 1 => Some(red_pill)
  })
  val red_pill = new Step(descriptions.red_pill, Vector("\"It's me, the player.\""))({
    case 1 => Some(player_name)
  })
  val player_name = new Step(descriptions.player_name, Vector())({
    case 1 => Some(am_i_free)
  })
  val am_i_free = new Step(descriptions.am_i_free, Vector("\"No.\""))({
    case 1 => Some(are_you)
  })
  val are_you = new Step(descriptions.are_you, Vector("\"No.\"", "\"No.\""))({
    case 1 =>
      isOver = true
      None
    case 2 =>
      isOver = true
      None
  })

  val canteens: Step = new Step(descriptions.canteens, Vector("Meal at Dipoli.", "Meal at Täffä."))({
    case 1 =>
      Some(dipoli)
    case 2 =>
      Some(taffa)
  })

  val dipoli: Step = new Step(descriptions.dipoli, Vector("Meat.", "Vegan."))({
    case 1 =>
      set_var("ate_meat", "1")
      Some(kill_geese_in_game)
    case 2 =>
      set_var("ate_meat", "0")
      Some(kill_geese_in_game)
  })

  val kill_geese_in_game: Step = new Step(descriptions.kill_geese_in_game, if (context.ate_meat) Vector("Kill geese.") else Vector("Kill geese.", "Don't kill geese."))({
      case 1 =>
        Some(kill_geese)
      case 2 =>
        Some(dont_kill_geese)
  })
  val kill_geese: Step = new Step(descriptions.kill_geese, if (context.endings_visited==alt_endings_count) Vector("Don't kick it.", "Kick it.", "I'm ready to fight.") else Vector("Don't kick it.", "Kick it."))({
    if (context.endings_visited==alt_endings_count){
      case 1 =>
        Some(dont_kick_it1)
      case 2 =>
        kick_it()
        None
      case 3 =>
        Some(ready_to_fight)
    }else{
      case 1 =>
        Some(dont_kick_it1)
      case 2 =>
        kick_it()
        None
    }
  })

  val dont_kick_it1: Step = new Step(descriptions.dont_kick_it1, Vector("Don't kick it."))({
    case 1 =>
      Some(dont_kick_it2)
    case 2 =>
      kick_it()
      None
  })

  val dont_kick_it2: Step = new Step(descriptions.dont_kick_it2, Vector("Don't kick it."))({
    case 1 =>
      Some(dont_kick_it3)
    case 2 =>
      kick_it()
      None
  })

  val dont_kick_it3: Step = new Step(descriptions.dont_kick_it3, Vector("Don't kick it."))({
    case 1 =>
      goodbyeMessage = endings.dont_kick_it
      isOver = true
      None
    case 2 =>
      kick_it()
      None
  })

  def kick_it(){
    goodbyeMessage = endings.kick_it
    isOver = true
  }

  val ready_to_fight: Step = new Step(descriptions.ready_to_fight, if (has_gooseslayer) Vector("Kick.", "Run.", "Use GOOSESLAYER.") else Vector("Kick.", "Run."))({
    if (has_gooseslayer) {
      case 1 =>
        goodbyeMessage = endings.kick_megagoose
        isOver = true
        None
      case 2 =>
        goodbyeMessage = endings.run_megagoose
        isOver = true
        None
      case 3=>
        Some(kill_megagoose)
    }else{
      case 1 =>
        goodbyeMessage = endings.kick_megagoose
        isOver = true
        None
      case 2 =>
        goodbyeMessage = endings.run_megagoose
        isOver = true
        None
    }
  })
  val kill_megagoose: Step = new Step(endings.kill_megagoose, Vector("CREDITS"))({
    case 1 =>
      goodbyeMessage = endings.credits
      isOver = true
      None
  })

  val dont_kill_geese: Step = new Step(descriptions.dont_kill_geese, Vector("\"How's this for entertainment?\""))({
    case 1 =>
      Some(entertaining)
  })

  val entertaining: Step = new Step(descriptions.start, Vector())({
    case 1 =>
      goodbyeMessage = endings.vegan_army
      isOver = true
      None
  })

  val taffa: Step = new Step(descriptions.taffa, Vector(s"Go sit with Colin.", "Take the bolognese to-go."))({
    case 1 =>
      if (colin_conversation_count == 0)
        Some(colin_first)
      else
        Some(colin_been_here)
    case 2 =>
      context.bolognese_variant = 1
      Some(eating_bolognese)
  })
  val eating_bolognese = new Step(descriptions.eating_bolognese, Vector("No can do, the o1 project cannot wait.", "Watch some Netflix!"))({
    case 1 =>
      goodbyeMessage = endings.tired
      isOver = true
      None
    case 2 =>
      Some(netflix)
  })
  val netflix = new Step(descriptions.netflix, Vector("Watch Bandersnatch.", "Watch a documentary about geese."))({
    case 1 =>
      goodbyeMessage = endings.circular_import
      isOver = true
      None
    case 2 =>
      goodbyeMessage = endings.documentary
      isOver = true
      None
  })
  val colin_first = new Step(descriptions.colin_first, Vector("\"Yeah, I was craving the bolognese.\"", "\"How did you know I almost went there?\""))({
    case 1 =>
      bolognese_variant = 3
      Some(eating_bolognese)
    case 2 =>
      Some(different_dishes)
  })
  val different_dishes = new Step(descriptions.different_dishes, Vector("\"Normally I would, but the bolognese is my weakness.\"", "\"I'll go try that next time.\""))({
    case 1 =>
      Some(colin_creepy)
    case 2 =>
      colin_hint = true
      Some(canteens)
  })
  val colin_creepy = new Step(descriptions.colin_creepy, Vector("\"What's up?\"", "\"Colin, that's kinda creepy.\""))({
    case 1 =>
      bolognese_variant = 5
      Some(eating_bolognese)
    case 2 =>
      bolognese_variant = 4
      Some(eating_bolognese)
  })
  val colin_been_here = new Step(descriptions.colin_been_here, Vector("\"Yeah, I was craving the bolognese.\"", "\"How did you know I almost went there?\""))({
    case 1 =>
      if(context.username == "Stefan") {
        Some(colin_something_different)
      } else {
        Some(colin_username)
      }
    case 2 =>
      Some(colin_had_conversation)
  })
  val colin_had_conversation = new Step(descriptions.colin_had_conversation, Vector("\"How many times have we had this conversation?\""))({
    case 1 =>
      bolognese_variant = 6
      Some(eating_bolognese)
  })
  val colin_something_different = new Step(descriptions.colin_something_different, Vector("\"How much do you know about me?\""))({
    case 1 =>
      Some(colin_you_hate_geese)
  })

  val colin_you_hate_geese = new Step(descriptions.colin_you_hate_geese, Vector("\"Yea, I do.\"", "\"I've hurt them. A lot.\""))({
    case 1 =>
      restart_mode = 2
      Some(start)
    case 2 =>
      goodbyeMessage = endings.mental_hospital
      isOver = true
      None
  })

  val colin_username = new Step(descriptions.colin_username, Vector("\"How do you know my name?\"", "\"I'm tired of hurting geese.\""))({
    case 1 =>
      restart_mode = 1
      Some(start)
    case 2 =>
      restart_mode = 3
      Some(start)
  })

  /** The character that the player controls in the game. */
  val player = new Player(starting_point)

  var goodbyeMessage = ""

  var isOver = false

  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
   * report of what happened, or an error message if the command was unknown. In the latter
   * case, no turns elapse. */
  def playTurn(command: String): (Boolean, String) = {
    def printDelimiter(options: Vector[String], upper: Boolean): String = {
      var s = if (upper) "┌" else "└"
      s += "-" * (options(0).length + 2)
      for(_ <- 1 until options.length) {
        s += (if (upper) "┬" else "┴")
        s += "-" * (options(1).length + 2)
      }
      s += (if (upper) "┐" else "┘")
      s + "\n"
    }

    val action = new Action(command)
    val outcomeReport = action.execute(this.player)

    if (isOver)
      return (false, "")
    //    var step =
    var output = outcomeReport._1
    output += "\n\n"
    val options = outcomeReport._2

    if(options.isEmpty)
      return (true, output)


    output += printDelimiter(options, true)

    val lines_count = options.map(s => s.flatMap(ch => if(ch == '\n') "\n" else "").length).max+1

    if(options.nonEmpty)
      output += "|"
    for(option <- options) {
      val lines = option.toUpperCase.split('\n')  // uppercase?
      val width = lines.map(_.length).max
      for(i <- 0 until lines_count) {
        output += format(" %" + width + "s |", lines.applyOrElse[Int, String](i, i => ""))
      }
    }
    output += "\n"
    output += printDelimiter(options, false)

    (false, output)
  }
}

