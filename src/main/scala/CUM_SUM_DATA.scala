
object CUM_SUM_DATA {
  def main(args:Array[String]):Unit={

    val arr = Array(2,7,9,11,13)


    for(i <- 0 until  arr.length-1) {
       if (arr(i) + arr(i+1)==9){

         print(arr(i)+ "," + arr(i+1))
      }
    }
    scala.io.StdIn.readLine()
  }
}
