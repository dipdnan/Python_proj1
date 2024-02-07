package Company_Scenrios

object Fourth_Largest_Element_Array {
  def main(args: Array[String]): Unit = {

    val arr = Array(10, 20, 50, 100, 200, 150, 250)

    val find4thLargest = fourthLargestElement(arr)
    println(s"The fourth largest element : $find4thLargest")
  }
  def fourthLargestElement(arr: Array[Int]): Option[Int] = {
    if (arr.length < 4) {
      None
    }
    else {
      val sortedArr = arr.sorted
      Some(sortedArr(arr.length - 4))
    }
  }
}
