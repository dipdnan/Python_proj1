import org.apache.spark.SparkContext
import org.apache.spark.{SparkConf, SparkContext}

object WORD_COUNT_SCALA_1 {
def main(args:Array[String]):Unit={
      // Create a Spark configuration and set a master node locally
      val conf = new SparkConf().setAppName("WordCount").setMaster("local[*]")

      // Create a Spark context
      val sc = new SparkContext(conf)

      // Input data
      val inputData = "I am deepak. I am data engineer"

      // Split the input data into words
      val words = sc.parallelize(inputData.split("\\s+"))

      // Count the occurrences of each word
      val wordCounts = words.map(word => (word, 1)).reduceByKey(_ + _)

      // Print the result
      wordCounts.collect().foreach(println)

      // Stop the Spark context
      sc.stop()
  }
}
