import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

object sample {

  def main(args: Array[String]): Unit = {

    // Create Spark configuration
    val sc = new SparkContext("local[8]", "Deepak")

    val input = sc.textFile("C:/Users/Ganesh/Desktop/sample1.txt");

    val words = input.flatMap(x => x.split((" ")))

    val wordsmap = words.map(x => (x, 1))
    val wordcount = wordsmap.reduceByKey((x, y) => x + y)

      words.collect.foreach(println) // output of flatmap
      wordcount.collect.foreach(println)
      scala.io.StdIn.readLine()


  }
}
