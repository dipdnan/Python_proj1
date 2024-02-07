package Company_Scenrios

object Remove_Duplicate_using_Array {
  def main(args: Array[String]): Unit = {

    val inputArray = Array(10, 20, 20, 50, 40, 30, 60, 70)
    val outputArray = removeDuplicates(inputArray)

    println("Input Array : " + inputArray.mkString(" "))
    println("Output Array: " + outputArray.mkString(" "))
  }

  def removeDuplicates(arr: Array[Int]): Array[Int] = {
    arr.distinct
  }
}
