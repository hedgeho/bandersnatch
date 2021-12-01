package bandersnatch

import bandersnatch.VarsIO.{get_var, set_var}

object context {
  val character_name: String = get_var("username")
  var restaurant = "Dipoli"
  var bolognese_variant = 1
  var restart_mode = 0
  var typing_delay = 0  // ms, 200 is good enough
  var boss_name = "MEGAGOOSE"

  var colin_hint = false

  var alt_endings_count = 14

  def endings_visited = get_var("endings_visited").toInt
  def show_tutorial = get_var("show_tutorial").toInt == 1
  def ate_meat = get_var("ate_meat").toInt == 1
  def colin_conversation_count = get_var("colin_conversation_count").toInt
  def username = get_var("username")
  def has_gooseslayer = get_var("has_gooseslayer").toInt == 1

  object descriptions {
    lazy val tutorial = "Before starting, a quick disclaimer: \n"+
      "This game involves topics of violence and animal cruelty: player discretion is advised. \n"+
      "Enjoy the game!\n\n" +
      "Hi! Thanks for trying our game out. Using soft wraps in the console is recommended for proper text display. \n" +
                    "The game revolves around choices you'll make with different options presented to you.\n" +
                   "To choose the first option, type \"1\". To choose the second one, type, \"2\".\n" +
                   "Any other input will be interpreted as choosing the first option. Try it out:"
    lazy val start = {
      (restart_mode match {
        case 0 => ""
        case 1 =>
          var res = "Colin: \"It's in the game files. You must have put it there.\"\n\n\n" +
          s"Colin: \"$username, you're not here to finish a game. You're here to destroy the MEGAGOOSE. Start again, and find it.\"\n\n\n"
          if(!has_gooseslayer) {
            set_var("has_gooseslayer", "1")
            res += "You have acquired the mighty GOOSESLAYER. Use it wisely."
          }
          res
        case 2 => "Colin: \"Go back and do what you must to them.\"\n\n\n"
        case 3 =>
          var res = "Colin: \"They will hurt you back nevertheless. They will control us, observe us, wage war against us. You might have seen it already.\"\n\n\n" +
          s"Colin: \"$username, you're not here to finish a game. You're here to destroy the MEGAGOOSE. Start again, and find it.\"\n\n\n"
          if(!has_gooseslayer) {
            set_var("has_gooseslayer", "1")
            res += "You have acquired the mighty GOOSESLAYER. Use it wisely."
          }
          res
      }) +
      s"It's Wednesday morning. $character_name has barely gotten any sleep after coding all night. The mountain of empty Coca-Cola cans on the desk mock $character_name as they get up from bed. The o1 project has taken more time than expected. $character_name is also yet to start working on the IEM accounting exercises, but thinks they'll get both projects done before they're due.\n\n\n" +
        s"$character_name drags themselves to the kitchen and startles: there's a goose sitting outside the window staring at $character_name, while producing a considerable pile of feces on the porch. As $character_name gets closer to the window to scare the goose away, it lazily turns around and waddles to the neighbors yard. It takes $character_name quite a long time to clean up the mess left by the goose.\n\n\n" +
        (if(endings.all_visited) s"There's a note on the kitchen table, one that $character_name had left for themselves: \"Kick a goose today.\" $character_name takes another look at the goose, now dropping its feces on the neighbor's porch, and tries to remember to do as the note advised.\n\n\n" else "") +
        s"$character_name needs help with the o1 project: luckily, the exercise session is now ongoing, where a TA could take a look at it. Even so, it is lunchtime, and $character_name is hungry."
    }
    lazy val exercise_session = s"A meal can wait, but the exercise session only happens now. $character_name can get to the Computer Science building fastest with an electric scooter. It does cost some money, though, and walking is good exercise."
    lazy val scooter = s"$character_name grabs the closest electric scooter, and starts zooming towards the CS building. They're starting to become quite stressed thinking of all the work still to be done with the o1 project. $character_name is creating a choose-your-adventure game, but struggling to come up with all its branches. Also, the game still needs a catchy ending, something that surely grabs the player's attention.\n\n\n" +
      s"Deep in thought, $character_name does not notice that one of the geese has suddenly walked onto the road. The electric scooter bumps into it, and $character_name fumbles onto the ground, gaining quite uncomfortable bruises. The goose flies away, making a sound that almost sounds like it's...laughing?\n\n\n" +
      s"Bruised and hungry, $character_name gets to the exercise session. There are quite a lot of students there, noses sunk deep in Scala: they look agitated. The TA is helping another student with their work. $character_name sits next to them, and takes out their laptop. The TA notices $character_name, peers at their code, and then the other student's work: the latter seems to be behind schedule by a lot.\n\n\n" +
      s"""TA:"$character_name, what if you completed the project with Aaron? They're in trouble with their project, but could likely help you out with writing the rest of the branches.""""
    lazy val vision = s"$character_name doesn't want anyone messing with their vision of the game. The TA seems understanding of this, and turns back to help Aaron with his project.\n\n" +
      s"$character_name is now hungry, stressed, bruised and coding the game alone. They see a text from a friend asking for help with the IEM accounting exercises - the ones $character_name hasn't started yet. This does not help with the stress. There are so, so many storylines still to be coded in the game.\n\n" +
      s"$character_name starts to question if the project is worth it at all."
    lazy val walk = s"$character_name starts strolling towards the CS building. They're starting to become quite stressed thinking of all the work still to be done with the o1 project. $character_name is creating a choose-your-adventure game, but struggling to come up with all its branches. Also, the game still needs a catchy ending, something that surely grabs the player's attention.\n\n" +
      s"Deep in thought, $character_name does not notice that one of the geese has dropped a smelly surprise onto the road. They step on the pile of feces, and curse: the smell won't be easy to get rid of. Someone ought to do something about these pesky geese!\n\n\n" +
      s"Smelly and hungry, $character_name gets to the exercise session. Only a few students are left there at this point, most of them sleeping. The TA is sitting at their desk, sipping a cup of coffee.\n\n" +
      s"$character_name asks the TA for help with the game, explaining how difficult it is to come up with all the storylines. The TA patiently listens to $character_name, and suggests cutting out some of the branches.\n" +
      s"\"Make some of the them lead to similar endings! It'll make your work much easier, and you'll surely get the project done in time,\" the TA advices.\n\n" +
      s"$character_name doesn't exactly like the idea, as it would restrict the player's feeling of free choice in the game. They still tell the TA they'd consider it.\n\n" +
      s"$character_name works on the project at the exercise session, getting many of the branches planned out. Although determined with their vision, the hunger and stress are getting to them, and so the coding work is quite exhausting. Also, they see a text from a friend asking for help with the IEM accounting exercises - the ones $character_name hasn't started yet. This does not help with the stress. There are quite a few storylines still to be coded in the game.\n\n" +
      s"$character_name starts to question if the project is worth it at all."
    lazy val ta_chat = s"$character_name decides to keep going with the project. It's probably a good choice: the concept is quite promising. They decide to go home to finish the game and pack up their stuff.\n\n" +
      s"On the walk home, $character_name encounters a goose: it's leaving more smelly piles on the street. $character_name is not happy to see this, and runs after the goose. It flies away too quickly for them to catch it. $character_name does notice something peculiar, though: while they were running to catch the goose, they stepped on all of the piles of geese feces on the street - like, all of them.\n\n" +
      s"In fact, it really seems like the goose dropped its piles precisely where $character_name would step, as if it knew each step in advance. Creepy.\n\n\n" +
      s"$character_name gets home, prepares a late lunch for themselves and continues coding the storylines. They're getting more and more complicated, and the amount of bugs $character_name is confronted with seems to be rising exponentially.\n\n" +
      s"$character_name messages their TA, asking for help. The TA answers and helps $character_name with debugging. A similar discussion continues for quite some time, until the TA gets frustrated and brings up their previous idea once more:\n\n" +
      s"\"$character_name, you should just restrict the freedom of the choices players can make, and lead each storyline to similar outcomes. The project will be much easier.\""
    lazy val free_will = s"$character_name sends a message to the TA, giving in to making the game simpler: at least the game will be finished this way. $character_name tells the TA that they'd still like to make a version of the game with free will being more engrained into it, to make it feel more like real life.\n\n" +
      s"The TA's answer is chilling.\n\n\n" +
      s"TA: \"$character_name, you do know you're not in control of yourself, right?\""
    lazy val red_pill = s"ERROR: exception raised on line 42, in control_character.scala, \"exception: red pill forced\";\n\n\n" +
      s"$character_name:\n\"Who said that? Who's out there?\""
    lazy val player_name = s"$character_name: \"The player? What does that mean? What's your name?\""
    lazy val am_i_free = s"$character_name:\n\"Am I free?\""
    lazy val are_you = s"$character_name:\n\"Are you?\""
    lazy val canteens = {
      (if(colin_hint) "Colin: \"Why not try it now?\"" else "") +
      s"Both Dipoli's and Täffä's student canteens are close to $character_name."
    }
    lazy val dipoli = s"$character_name decides to go eat at Dipoli. On the way there, they're passed by two geese walking to the opposite direction on the sidewalk. They yell at $character_name quite aggressively. As aggravating as the smelly creatures are, $character_name is too deep in though planning their choose-your-adventure game to care.\n\n" +
      s"$character_name arrives at Dipoli.\n" +
      s"The restaurant has a meat option and a vegan option."
    lazy val kill_geese_in_game = s"After eating their ${if (context.ate_meat) "meat" else "vegan"} meal, $character_name drops by the exercise session at the CS building to ask for advice with their project. Only a few students are left there at this point, most of them just struggling with the IEM accounting exercises. The TA is sitting at their desk, sipping a cup of tea." +
      s"\n\n$character_name asks the TA for help with the game, explaining how difficult it is to come up with a catchy ending for each storyline. The TA patiently listens to $character_name, and suggests giving the ending a proper shock factor." +
      s"\n\n\"Take something you really hate and make every storyline end with the main character destroying that! It's gonna be easier to write of a thing that you feel strongly about, and you'll surely get the project done in time,\" the TA advices." +
      s"\n\n$character_name doesn't exactly like the idea, as it might feel weird to some players to inflict so much hate on a the same thing. They still tell the TA they'd consider it." +
      s"\n\n\nThe exercise session ends, and $character_name packs up their stuff. On the way home, they notice a peculiar thing on the ground: piles goose feces, set on a pattern of sorts. It almost seems like the piles are there for someone to consecutively step on all of them. $character_name laughs at the ridiculousness of the thought. The geese are too dumb to plan something like that." +
      s"\n\nThat gave $character_name an idea, though: what if, at the end of each storyline, a goose gets killed? It would be severe enough to have shock factor, but ridiculous enough to not feel too serious." +
      s"\n\n\nAt home, $character_name messages the TA to ask for their opinion. Honestly, $character_name isn't too keen on their own idea, but the TA seems to love it."
    lazy val kill_geese = s"As $character_name plays around with the idea of killing geese in the game, they realize how therapeutic it is. They really do hate them!\n\nEven though the ending is now figured out, there's a lot of code to write with the storylines. The IEM exercise waiting to be done, $character_name decides to prioritize the game and sends a message to the IEM professor, letting them know $character_name is dropping the course.\n\n$character_name has never before felt as free." +
      s"\n\n\nWith IEM off the way, $character_name finishes the game in no time. It's fun to play, and doesn't seem to have any major bugs. The interaction with a goose at the end of each storyline turns out to be quite an interesting mechanic, making the player always wonder if there's more of similar moments to find." +
      s"\n\n\nAfter finishing the game, $character_name takes a well-deserved walk around the campus. At Alvar Square, they're confronted by a goose. No wait, two geese, actually three - it's a huge hoard of them. They're in large numbers, but seem scared, as if they'd heard of $character_name's game. One of them bravely steps forward, though." +
      s"\n\n\n$character_name feels a strong urge to kick the goose."
    lazy val dont_kill_geese = s"$character_name tells the TA that no geese will be hurt during the game. The TA thinks the game would be too boring without a similar ending.\n\n\n" +
      s"TA:\n\"It wouldn't be entertaining enough!\""
    lazy val dont_kick_it1 = s"Maybe $character_name should reconsider. The goose is getting closer..."
    lazy val dont_kick_it2 = s"Seriously, it looks super mad."
    lazy val dont_kick_it3 = s"Like, that's probably the goose equivalent of Hercules."
    lazy val megagoose = s"The herd of geese surround $character_name, leaving space in the ring for someone - or something - else. The ground shakes, as the goose from $character_name's dream digs its way to the surface." +
      s"\n\n\nThe MEGAGOOSE emerges."
    lazy val ready_to_fight = s"The herd of geese surround $character_name, leaving space in the ring for someone - or something - else. The ground shakes, as the goose from $character_name's dream digs its way to the surface.\n\n\n" +
      s"The MEGAGOOSE emerges."
    lazy val taffa = s"$character_name decides to go eat at Täffä. It's Wednesday, so there's bolognese served there: $character_name loves it, as do all students here. On the way, a goose jumps out of the bush, scaring $character_name. They yell at it, and it flies away. The geese seem more aggressive than usual today. Maybe they're also craving the bolognese.\n\n" +
      s"$character_name arrives at Täffä. The line is long, reaching outside the building. While waiting in line, $character_name notices an increasing amount of geese gathering around the building. It was almost as if they were waiting for something - or someone. They're all looking at $character_name.\n\n" +
      s"After getting their pasta, $character_name looks over at the canteen and sees a friend of theirs from the the same program: Colin.\n\n\n" +
      s"Colin is nice, but sometimes slightly intense."
    lazy val eating_bolognese = {
      (bolognese_variant match {
        case 1 =>
          s"$character_name doesn't feel like talking with Colin right now, they can be quite a lot at times. $character_name carries their tray back to the service line and asks for the food to be packed to-go. With the to-go pack, $character_name heads home.\n\n" +
            s"Outside Täffä, the geese that were waiting there have dispersed, only their smelly feces remain. $character_name tries to step over the piles, but slips and falls straight onto them. Covered in feces and furious, $character_name quickly leaves.\n\n" +
            s"At home they take a long shower - although the the smell doesn't completely disappear  -  and heat up the bolognese for themselves.\n\n\n"
        case 2 =>
          s"Outside Täffä, the geese that were waiting there have dispersed, only their smelly feces remain. $character_name tries to step over the piles, but slips and falls straight onto them. Covered in feces and furious, $character_name quickly heads home.\n\n\n"
        case 3 =>
          s"Colin: \"Cool. I came for that, too.\"\n$character_name looks at Colin who, once again, doesn't have any food in front of them.\n\nThey eat in silence, after which $character_name leaves. Maybe Colin would've had more to say.\n\n\n" +
            s"Outside Täffä, the geese that were waiting there have dispersed, only their smelly feces remain. $character_name tries to step over the piles, but slips and falls straight onto them. Covered in feces and furious, $character_name quickly heads home.\n\n\n"
        case 4 =>
          s"Colin: \"Call it whatever you want. I'll see you next time.\"\n\n\n"
        case 5 =>
          s"Colin: \"You have to work on your o1 project. When we meet again, I'll tell you.\"\n\n\n"
        case 6 =>
          set_var("colin_conversation_count", s"${colin_conversation_count + 1}")
          s"Colin:\n\"$colin_conversation_count times, I think. But that might change. We'll meet again when you're next craving the bolognese.\""
      }) +
      s"After the gluten-heavy bolognese, $character_name feels tired."
    }
    lazy val netflix = "There's a lot to watch on Netflix."
    lazy val colin_first = s"Colin isn't eating anything, just staring at a wall. $character_name carries their tray to Colin's table, sits down and greets them.\n\n\n" +
      s"Colin: \"So, decided not to eat Dipoli?\""
    lazy val different_dishes = s"Colin: \"There's different dishes to choose from at Dipoli. I thought you might go for the vegan option.\""
    lazy val colin_creepy = s"Colin: \"I know. That's why I was waiting for you here. I know you'll come back here, too. I have a lot to tell you.\""
    lazy val colin_been_here = s"Colin isn't eating anything, just staring at a wall. $character_name carries their tray to Colin's table, sits down and greets them.\n\n\n" +
      s"Colin: \"So, decided not to eat Dipoli?\""
    lazy val colin_something_different = "\"How much do you know about me?\"\n\n" +
      s"Colin:\n\"I know you hate the geese around here.\""
    lazy val colin_you_hate_geese = "Colin:\n\"I know you hate the geese around here.\""
    lazy val colin_had_conversation = s"Colin: \"The same way you knew I'd be here: we've had this conversation before.\""
    lazy val colin_username = s"Colin: \"$username, you came to see if something would be different this time around. And something is, isn't it?\""
  }

  object endings {
    lazy val teamwork = {
      if(get_var("teamwork_visited") == "0") {
        set_var("teamwork_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"Still shaken after falling with the scooter, $character_name decides to take some work off their shoulders and ends up completing the project with Aaron.\n" +
        s"Maybe the game will actually be finished now!\n\n" +
        s"Unfortunate for $character_name, there's a reason why Aaron's project was behind schedule: they're not that good at coding, while amazing at procrastinating. $character_name's vision is destroyed by Aaron's tardiness. The game turns out lacking, as many of the branches had to be cut. The project gets graded 120/200 points.\n\n" +
        s"$character_name is not happy. In fact, they're quite mad: the accident with the goose clouded their judgement, and without it the game wouldn't have had that other student to weigh it down. It always comes back to the geese.\n\n" +
        s"$character_name finds the closest goose, and hits it with their laptop. The project is still inadequate, but at least $character_name got their revenge."
    }
    lazy val eating_goose = {
      if(get_var("eating_goose_visited") == "0") {
        set_var("eating_goose_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"Somehow $character_name finds the determination to keep going. They decide to go home to finish the game and pack up their stuff.\n\n" +
        s"As $character_name is opening the door of the CS building to leave, the it hits something. On the other side of the door is a goose. And now it's mad. The goose attacks $character_name.\n\n" +
        (if(ate_meat) {
        s"$character_name has had it with the geese. It's been a long day, there's no time for this. Their rage fueled by the bruises still hurting on their skin, $character_name grabs the goose and strangles it. The goose dies as it makes one last ridiculing sound. $character_name feels the stress in their body leave. Who would've guessed hurting geese would be so relaxing?\n\n" +
          s"$character_name quickly hides the dead goose in the nearby bush and heads home.\n\n\n" +
          s"Feeling less stressed, $character_name gets the game done. Even so, with a grumbling stomach and the IEM accounting exercises still lurking behind the corner, the quality of their code isn't the greatest.\n\n" +
          s"Although the story turns out great, the gameplay is buggy and the game crashes frequently. The project gets graded 100/200 points."
      } else {
        s"$character_name has had it with the geese. It's been a long day, there's no time for this. Their rage fueled by the bruises still pumping on their skin, $character_name grabs the goose and strangles it. The goose dies as it makes one last ridiculing sound.\n\n" +
          s"Observing the goose, $character_name hears their stomach grumbling. They're no vegan, and quite like the taste of meat. $character_name decides to eat the goose - it's actually quite tasty - and heads home.\n\n" +
          s"Feeling nourished, $character_name gets to coding. The project is advancing at a respectful speed, as they finish the storylines one by one. As the game is nearly done, $character_name feels a their stomach complain. Maybe raw geese meat isn't a good substitute for lunch?\n\n" +
          s"$character_name spends the rest of the evening on the toilet. The game is never finished, but gets graded 20/200 points. They're all pity points."
      })
    }
    lazy val vegan_army = {
      if(get_var("vegan_army_visited") == "0") {
        set_var("vegan_army_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"$character_name conjures an army of vegans that storm the TA's apartment and eat them. Pretty ironic, to be honest." +
        s"\n\nThe police capture $character_name for arranging the vegan riot. Since there is no TA, no student on the o1 course gets a grade for the project."
    }
    lazy val kick_megagoose = {
//      if(get_var("kick_megagoose_visited") == "0") {
//        set_var("kick_megagoose_visited", "1")
//      }
    s"$character_name kicks the MEGAGOOSE. It doesn't even twitch. The MEGAGOOSE turns around and sits on $character_name.\n\n\n" +
      "GAME OVER"
    }
    lazy val run_megagoose = {
//      if(get_var("run_megagoose_visited") == "0") {
//        set_var("run_megagoose_visited", "1")
//      }
    s"As $character_name starts running away, the normal-sized geese grab their legs. $character_name falls down and turns around to face the MEGAGOOSE. It opens its peak and swallows $character_name whole.\n\n\n" +
      "GAME OVER"
    }
    lazy val kill_megagoose = {
//      if(get_var("kill_megagoose_visited") == "0") {
//        set_var("kill_megagoose_visited", "1")
//      }
    s"$character_name raises the GOOSESLAYER. With a strong swing, the MEGAGOOSE slices neatly in half and falls to the ground. The normal-sized geese around it get scared and start frantically flapping their wings. They're not flying south, though, but straight up, towards the sky. In fact, geese all around Otaniemi are seen flying away, looking for a new leader.\n\nThe students of Aalto never saw the geese again. Within a week, their remaining feces had disappeared from the lawns.\n\n$character_name never finished their o1 project. They were too busy trying to defeat the MEGAGOOSE.\n\n\n\n"
    }
    lazy val credits = {
    "Thank you for playing\nBANDERGOOSE\n\n--------------------------------\n\nby Ilia Zalesskii\nand\nAaron Wallasvaara\n\n--------------------------------\n\nNo geese were hurt in the making of this game."
    }
    lazy val kick_it = {
      if(get_var("kick_it_visited") == "0") {
        set_var("kick_it_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"The goose lets out a quiet shriek as $character_name's boot hits it and it falls to the ground. The other geese seem even more scared: for some reason, they're honking at the ground, like expecting help from the devil himself. But even the devil didn't dare to emerge, and so the geese flee.\n\n\n" +
        s"$character_name's game is a huge hit: everyone in the o1 class love playing it. It gets graded the full 200/200 points, and is voted within the best games of the class.\n\nEven so, something is still bugging $character_name. It doesn't really feel like the geese are gone."
    }
    lazy val dont_kick_it = {
      if(get_var("dont_kick_it_visited") == "0") {
        set_var("dont_kick_it_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"The goose attacks $character_name. Another student nearby sees this, and comes help. They do what $character_name couldn't, and kick the goose.\n\nSuddenly the other geese don't look as scared. They start screaming in unison and attack all students nearby. Students grab their shoes, backpacks, computers, or anything else that can be used as a weapon against the wild geese.\n\nThe mayhem quickly spreads to other areas, eventually not being contained within Otaniemi. Geese are seen attacking people around Espoo, Helsinki and Vantaa, and in the following weeks all parts of Finland. The country is covered in feces, spreading diseases the human body cannot withstand.\n\nAs Sweden promises to help with the fight against the restless birds, their native geese population joins the battle, as well. Soon enough, the whole world is in war with geese from all over, from the Orinoco geese of Brazil to the Egyptian geese of the Sahara desert." +
        s"\n\n\nThe war is imaginatively dubbed WWG, World War Geese. There is no reason to believe it will end soon: the geese have revealed themselves more resourceful than ever thought possible. Some believe the source for the geese's power to be their leader, and seek to destroy it - but alas, it's yet to show its beak during the war, and is rumored to be hiding underground.\n\n$character_name's project would have been graded the full 200/200 points, but $character_name wouldn't have been there to enjoy it: they're recognized as the first casualty of WWG."
    }
    lazy val give_up = {
      if(get_var("give_up_visited") == "0") {
        set_var("give_up_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"$character_name decides to drop the project. It's a shame: the concept for the game seemed quite promising. $character_name is feeling quite down for having to scrap their vision. The hunger and bottled up stress from school finally rise to the surface, and $character_name starts crying. They decide to go home to rest and pack up their stuff.\n\n" +
        s"As $character_name is leaving the building, they're confronted by a goose. A smelly, loud, annoying goose. It looks back at $character_name as if challenging them.\n\n\n" +
        s"Still crying, $character_name releases their emotions on the goose. Their rage fueled by the bruises still hurting on their skin, $character_name grabs the goose and strangles it. Even though it can't breathe, the goose calmly looks at $character_name: it's surely just making fun of $character_name's ineptitude. What a despicable creature.\n\n" +
        s"Even when the goose dies, $character_name is still crying. It's been a long day. They find a matchbox from their pocket, set the remains of the goose on fire and sit next to it. The fire smells retched, but $character_name feels much better already.\n\n\n" +
        s"The game was never even turned in, and so is graded 0/200 points. $character_name feels horrible. All their clothes smell like goose."
    }
    lazy val otacruise = {
      if(get_var("otacruise_visited") == "0") {
        set_var("otacruise_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"In fact, $character_name already has a passing grade from the o1 course: they should really be focusing on the IEM accounting exercises right now. They go home, prepare some lunch, and finish the exercises in time.\n\n" +
        s"$character_name thought they'd have to skip Otacruise to get all schoolwork done, but with the decision to accept a passing grade from o1, they have time to go to the cruise now. How fun!\n\n\n" +
        s"Next morning at the docks, $character_name's friends are ready to board the ship. $character_name approaches them, and they start yelling: \"What the fuck is that smell dude, get away ewww!\" The friends back up to the boarding terminal without waiting for $character_name.\n\n" +
        s"$character_name looks down at their shoes, still covered in goose feces. They get angry at the geese: why do they have to be everywhere, ruining even time taken off from studying?\n\n" +
        s"$character_name hears laughing, although not that of a human: next to the docks, a goose stares at $character_name's misery and is laughing its lungs out. How does it even understand the situation, nevertheless is laughing?\n\n" +
        s"$character_name yells, grabs the goose and shoves its head into the water. As the goose drowns, it stares at $character_name as if - despite it's inevitable death - it had won.\n\n\n" +
        s"$character_name gets rid of the smelly shoes, and in the end the cruise turns out a healthy break from studying.\n\n" +
        s"The game was never turned it, and so it's graded 0/200 points - but that's ok. $character_name has a passing grade, and prioritized their well-being, which is not always easy to do. It's important to rest!\n\n" +
        s"Unless you're a goose. Geese don't deserve sleep. Or hugs. Or chocolate."
    }
    lazy val unfinished = {
      if(get_var("unfinished_visited") == "0") {
        set_var("unfinished_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"$character_name defies the TA's advice: the story is supposed to feel lifelike, so that's how it'll be! They start frantically writing the storylines. Branches on branches, bugs on bugs; $character_name is feeling more and more exhausted.\n\n" +
        s"They open a window to let in some fresh air - but, it's not fresh at all. The goose from this morning has returned to drop off more of its lovely presents, and so the stench spreading inside is dreadful. $character_name curses and closes the window.\n\n\n" +
        s"$character_name codes all night, but does not have enough time to finish all of the storylines. Too proud to turn in an unfinished game, they don't turn in anything at all. The project is graded 0/200 points.\n\n\n" +
        s"Frustrated, $character_name opens their curtains, only to find the goose still sitting there. It's visibly happy for $character_name's suffering. In anger, $character_name grabs a kitchen knife and throws it at the goose: it cuts neatly in half and falls to the ground.\n\n" +
        s"Feels like it's foreshadowing for another moment yet to happen."
    }
    lazy val tired = {
      if(get_var("tired_visited") == "0") {
        set_var("tired_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"$character_name decides to finish the project, but they're too tired to go to the exercise session for advice. With the engagement comparable to that of a snails, $character_name writes the game and turns it in.\n\n\n" +
      s"The game turns out terrible: it's buggy and the story feels as sluggish as $character_name was when writing it. They really should've asked for some help when making it. The project gets graded 40/200 points. It's time for $character_name to take a nap: maybe the next project will turn out better.\n\n\n" +
      s"$character_name dreams of a swarm of geese all chasing $character_name at Täffä. The geese are almost catching up to $character_name, when they stop and slowly bow. In the direction they're bowing, a gigantic goose towers over them. It seems to be the leader of the geese. $character_name wakes up, covered in cold sweat."
    }
    lazy val circular_import = {
      if(get_var("circular_import_visited") == "0") {
        set_var("circular_import_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"ERROR: raised an exception on line 790, in main.scala, \"circular function call exception: cannot launch Bandersnatch.exe from within the Bandersnatch.exe\";"
    }
    lazy val documentary = {
      if(get_var("documentary_visited") == "0") {
        set_var("documentary_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      s"Narrator: \"For more protection, geese live in herds. Although they can be irritating - and even scary at times - by themselves, their true power hides in their numbers. Geese will choose a territory for the herd to settle in, from which they will not leave until it's completely covered in their feces.\n\n" +
        s"Above all, geese are loyal to their leader, the goose equivalent of a queen bee. When their leader decides on an enemy for the herd, the peasants don't question the leader's judgment: they just obey.\n\n\n" +
        s"$character_name, beware: the geese are coming for you.\"\n\n\n" +
        s"$character_name pauses the documentary. Did...did the narrator just call them by their name? $character_name tries to rewind the documentary back, but before they could, the front door is kicked in - or whatever it's called when a herd of geese all jump at a door at the same time to smash it open. Frozen in place, $character_name can only scream while the geese jump on their body and devour it, limb by limb.\n\n\n" +
        s"$character_name is dead, and can't turn in the o1 project: it gets graded 0/200 points. Shame on you, $character_name."
    }
    lazy val mental_hospital = {
      if(get_var("mental_hostpital_visited") == "0") {
        set_var("mental_hospital_visited", "1")
        set_var("endings_visited", s"${endings_visited+1}")
      }
      "Colin:\n\"No, you haven't. It's winter. The geese flew south months ago.\"\n\n\n" +
        s"$character_name finds themselves in a completely white room, sitting at a table. $character_name's movements are restricted by some kind of a weird jacket that locks their hands on their chest. Opposite to $character_name sits someone wearing a white lab coat. They look concerned, and keep repeating something about \"geese not existing\".\n\nThey're wrong. They'll see."
    }
    def all_visited: Boolean = {
      get_var("endings_visited").toInt == alt_endings_count
    }
  }
}
