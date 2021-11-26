package o1.adventure

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an "area" can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Step(var description: Unit => String, var options: Vector[String], var action: Int => Option[Step]) {



  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.description().replaceAll("\n", " ").take(150)
}
