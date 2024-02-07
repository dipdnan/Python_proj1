package Scala_Programs

object Reverse_ArrayNumber {
def main (args:Array[String]):Unit={

  val arr = Array(1,2,3,4,5)

  for(i <-0 until arr.length)
  {
   println(arr(i) + " ")
  }

     for(i <-arr.length -1 to (0) by -1){

    println(arr(i) + ",")  //  i =4 arr(4) =5
    // i =3 arr(3) =4    i=2 arr(2)=3  i=1 arr(1)=2   i=0 arr(0)= 1
  }
//scala.io.StdIn.readLine()

}
}
