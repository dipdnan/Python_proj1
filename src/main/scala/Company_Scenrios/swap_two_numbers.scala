package Company_Scenrios
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions
import org.apache.spark.sql.expressions.Window
import org.apache.log4j.{Level,Logger}
object swap_two_numbers {
def main(args:Array[String]):Unit={

  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("WebUI").master("local[*]").getOrCreate()
  import spark.implicits._

  val a = 10
  val b = 20

  println(s"Before swapping: a= $a ,b= $b")

  // Swapping using a tuple
  val (newA, newB) = (b, a)

  println(s"After swapping number: a = $newA , b = $newB")

  //  var a =10
  //  var b =20
  //  var temp = a
  //   a = b
  //   b = temp
//  println(s"After swapping number: a = $a , b = $b")
}}
