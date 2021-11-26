package o1.adventure

object context {
  val character_name = "Stefan"
  val neighbor_name = "Henry"
  var helper_name = "Christian"
  var restaurant = "Dipoli"
  var understood = true

  object descriptions {
    def tutorial = "Hi! Thanks for trying my game out. On every step you will be given choice of two options.\n" +
                   "To choose the first one type \"1\". To choose the second one type \"2\".\n" +
                   "Any other input will be interpreted as choosing the first option. Try it out:"
    def home = (if(!understood) "Don't worry, you will.\n\n" else "") +
               f"$character_name: Whoa, i just woke up and it is already afternoon!\n" +
               f"$character_name: I guess I'll skip breakfast today and go straight to the lunch."
    def lunch = f"$character_name enters $restaurant.\n" +
                f"After getting a full plate of food he sits down on a chair, about to start his meal.\n" +
                f"$helper_name: Hi $character_name! Do you mind if I join you?\n" +
                f"$character_name: Hey, I didn't notice you, $helper_name! Sure, I got a spare chair.\n" +
                f"$helper_name: So, is it your lunch or breakfast?\n" +
                f"$character_name: I've been working on my O1 project the whole night...\n" +
                f"$helper_name: Cool, what's your idea?\n" +
                f"$character_name: It is about one episode of my favourite tv show from Netflix, called Bandersnatch...\n" +
                f"$helper_name: Wow, that's a great idea! Let's do it together! I haven't started my O1, so we can join our efforts!\n" +
                f"$helper_name: What do you think? It's gonna be great!"
  }

  object endings {
    val teamwork = f"At some windy December day $character_name gets feedback from his teaching assistant:\n" +
                   "\"Your project is clearly not finished, it crashes every now and then.\n" +
                   "I understand that you apparently did it in a rush during Otacruise, but you were given two weeks!\"\n\n" +
                   "Bandersnatch game gets 20/200 points."
  }
}
