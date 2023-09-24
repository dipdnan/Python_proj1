package Scala_Programs

object Find_2_elements_Array {
  def main(argss:Array[String]):Unit={

    val arr = Array(1,2,3,4,5)
//    val ar : Int [4]=  new Array(10,3,4,5)

    for(i <-0 until(arr.length)) {
     if(arr(i)==5 || arr(i)==3){
    println(s"These values present at $i th = "+ arr(i) )
  }}
}
}
