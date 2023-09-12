import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

object word_count_using_map {

  def main(args:Array[String]):Unit={

//    val sc = new SparkContext("local[8]","Deepak")
//
//    val rdd = sc.parallelize(Array("google.com","google.com","msn.com","google.com","google.com","google.com"))
//
//
//    val wordCounts = rdd.map(x => (x, 1))
//
//    val reducedCounts = wordCounts.reduceByKey(_ + _)
//    val sk = reducedCounts.sortByKey() .foreach(println)

    // Create Spark configuration
    val sc = new SparkContext("local[8]", "Deepak Nanaware")

    val input = sc.textFile("C:/Users/Ganesh/Desktop/SRC_Files/abc.txt");

    val words = input.flatMap(x => x.split((" ")))
    words.collect.foreach(println)

    val wordsmap = words.map(x => (x, 1))
    wordsmap.collect.foreach(println)

    val wordcount = wordsmap.reduceByKey((x, y) => x + y)
    wordcount.collect.foreach(println)

    scala.io.StdIn.readLine()
  }
}




