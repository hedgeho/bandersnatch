package o1.adventure


/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  *
  * @param input a textual in-game command such as "go east" or "rest" */
class Action(input: String) {
  private val commandText = input.trim.toLowerCase

  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as "You go west."). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  def execute(actor: Player): Tuple2[String, Vector[String]] = {
    var next_step =
      if (commandText == "2" && actor.location.options.length > 1) {
        actor.location.action(2)
      } else
        actor.location.action(1)

    next_step match {
      case Some(step) =>
        actor.go(step)
        (step.description(), step.options)
      case None => // game over, printing will be handled by UI
        ("", Vector())
    }
  }
}

