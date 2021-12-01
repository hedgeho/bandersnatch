package bandersnatch

import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.{Files, Paths}
import scala.io.Source

//  variables file IO functions
object VarsIO {
  val variables_file = "vars.txt"

  def set_var(name: String, value: String) = {
    val source = Source.fromFile(variables_file)
    var lines = try source.mkString finally source.close()

    var found = false
    val split = lines.split('\n').map(s => {
      val arr = s.split("=")
      val key = arr(0)
      //      val v = arr(1)
      if (key == name) {
        found = true
        key + "=" + value
      } else
        s
    })
    lines = split.mkString("\n")
    if (!found) {
      lines += "\n" + name + "=" + value
    }

    Files.write(Paths.get(variables_file), lines.getBytes(UTF_8))
  }

  def get_var(name: String): String = {
    val source = Source.fromFile(variables_file)
    val lines = try source.mkString finally source.close()

    var res = "0"
    lines.split('\n').foreach(s => {
      val arr = s.split("=")
      val key = arr(0)
      val value = arr(1)
      if (key == name) {
        res = value.trim
      }
    })
//    if(name == "ate_meat")
//      println(s"result: |$res|")
    res
  }
}
