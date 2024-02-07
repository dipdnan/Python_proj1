package Company_Scenrios

object Dictionary_Processing_SCALA {
def main(args:Array[String]):Unit={

  val mydictionary = Map(
    1 -> "HBO Max",
    2 -> "Netflix",
    3 -> "Prime Video",
    4 -> "Hotstar",
    5 -> "Hulu TV"
  )
  printValueForKey(mydictionary,2)
  printValueForKeyCombination(mydictionary,4,"Disney")
  printValueForKey(mydictionary,6)
  }
  def printValueForKey(dictionary : Map[Int,String],key : Int):Unit= {
    dictionary.get(key) match {
      case Some(values) => println(values)
      case None => println("Key Out of bound. Update the list.")
    }
  }
    def printValueForKeyCombination (dictionary : Map[Int,String],key:Int,additionalValues :String):Unit={
      dictionary.get(key) match {
        case Some(values) => println(s"$values + $additionalValues")
        case None => println("Key Out of bound. update the list")
      }
    }
}
