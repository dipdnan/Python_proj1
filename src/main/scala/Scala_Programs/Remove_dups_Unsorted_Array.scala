package Scala_Programs
import scala.collection.mutable

/* Remove Duplicates from an Unsorted Array using scala */
object Remove_dups_Unsorted_Array {
  def main(args:Array[String]):Unit= {

//    def removeDuplicates(arr: Array[Int]): Array[Int] = {
//      val uniqueSet = mutable.Set[Int]()
//      val resultBuffer = mutable.ArrayBuffer[Int]()
//
//      for (element <- arr) {
//        if (!uniqueSet.contains(element)) {
//          uniqueSet.add(element)
//          resultBuffer += element
//        }
//      }
//      resultBuffer.toArray
//    }
//
//    // Test the function
//    val inputArray = Array(3, 1, 2, 2, 4, 3, 5, 5)
//    val resultArray = removeDuplicates(inputArray)
//    println(resultArray.mkString(", ")) // Output: 3, 1, 2, 4, 5

    def removeDuplicates(arr: Array[Int]): Array[Int] = {
      val uniqueSet = arr.toSet // convert into SET
//            println(uniqueSet)
      uniqueSet.toArray
    }
    // Example usage:
    val unsortedArray = Array(3, 1, 2, 2, 4, 3, 5, 5)
    val deduplicatedArray = removeDuplicates(unsortedArray)
//    deduplicatedArray.foreach(x => if (x % 2 ==0) println(x) )
    deduplicatedArray.foreach(x=> print(x+","))

    val bb = unsortedArray.length -1
    println("\n =========== Unsorted Array Values ========  ")

    for(a<-0 to(bb)){
          print(unsortedArray(a)+",")    }
         println("\n New output ")
    unsortedArray.foreach(x=> println(x))

//    println(deduplicatedArray.mkString(",")) // Prints: 3, 1, 2, 4, 5

  }
}


