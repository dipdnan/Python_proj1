import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

object customizeway_parellrisemethod {

  def main(args:Array[String]):Unit={

    // Create Spark configuration
    val sc = new SparkContext("local[8]", "karthik")

//    val rdd = sc.parallelize(Array(1, 2, 3, 4, 5, 1))
//    val sum = rdd.reduce(_ + _)
//    val count = rdd.count()
//    val average = sum / count.toDouble  //.toDouble output will be 2.66
//    val average = sum / count         // output will be 2
//    println("Average value: " + average)  // Average value: 2.6666666666666665


    // filter the values in given array using filter method.
//    val rdd = sc.parallelize(Array(1, 2, 3, 4, 5))
//    val filteredRdd = rdd.filter(x => x % 2 != 0) // filter out the records which is given in array
//
//    val count = filteredRdd.count()
//    println("Count of odd numbers: " + count)  // Count of odd numbers: 3
//    filteredRdd.collect.foreach(println)  // 1,3,5


    // joining the two rdd's

//    val rdd1 = sc.parallelize(Array((1, "apple"), (2, "banana"), (3,"orange")))
//    val rdd2 = sc.parallelize(Array((1, "red"), (2, "yellow"), (4,"green")))
//
//    val joinedRdd = rdd1.join(rdd2)
//    joinedRdd.foreach(println)   // (2,(banana,yellow))
//                                // (1, (apple, red))


    //given an RDD of numbers, remove duplicate

//    val rdd = sc.parallelize(Array(1, 2, 3, 2, 1, 4, 5))
//    val distinctRdd = rdd.distinct()
//    distinctRdd.foreach(println)

    // SET operations on RDD

//    val rdd1 = sc.parallelize(Array(1, 2, 3, 4, 5))
//    val rdd2 = sc.parallelize(Array(3, 4, 5))
//    val subtractedRdd = rdd1.subtract(rdd2)
//    subtractedRdd.foreach(println)

    //union the RDD

//    val rdd1 = sc.parallelize(Array(1, 2, 3))
//    val rdd2 = sc.parallelize(Array(3, 4, 5))
//    val unionRdd = rdd1.union(rdd2)
//    unionRdd.foreach(println)

    // // given rdd cartesion
//    val rdd1 = sc.parallelize(Array(1, 2, 3))
//    val rdd2 = sc.parallelize(Array("A","B"))
//    val cart = rdd1.cartesian(rdd2)    // 1,A 1,B 2,A 2,B 2,C 3,A 3,B
//    cart.foreach(println)

    // given rdd intersection/ COMMON RECORDS FROM BOTH RDD
//    val rdd1 = sc.parallelize(Array(1, 2, 3))
//    val rdd2 = sc.parallelize(Array(1,2,3,4,5))
//    val intersectrdd = rdd1.intersection(rdd2)
//    intersectrdd.foreach(println)

//    val rdd = sc.parallelize(Array("apple", "banana", "orange",
//      "pear", "grape"))
//    val searchTerm = "orange"
//    val filteredRdd = rdd.filter(x => x == searchTerm)  // output will be orange only
//    filteredRdd.foreach(println)

//    val rdd = sc.parallelize(Array("apple", "banana", "orange","mango", "grape"))
//    val searchTerm = "an"
//    val filteredRdd = rdd.filter(x => x.contains(searchTerm)) // output will be banana mango orange
//    filteredRdd.foreach(println)



  }
}
