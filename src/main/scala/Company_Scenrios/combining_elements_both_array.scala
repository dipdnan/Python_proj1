package Company_Scenrios

object combining_elements_both_array {
def main(args:Array[String]):Unit={

  val list1 = List(1,2,3)
  val list2 = List('a','b','c')

  val result = list1.zip(list2)
  println(result)

}
}
