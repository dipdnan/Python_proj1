import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

object word_count {

  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local[8]", "Deepak")

    val input = sc.textFile("C:/Users/Ganesh/Desktop/SRC_Files/abc.txt");

    val words = input.flatMap(x => x.split((" ")))

    val lowercase = words.map(x => x.toLowerCase())

    val words1 = lowercase.map(x => (x, 1))
    words1.collect.foreach(println)

        val words2 = words1.reduceByKey((x, y) => x + y)

        val srt = words2.sortBy(x=>x._2 )

        val t = srt.take(1).foreach(println)

        scala.io.StdIn.readLine()
      }
  }
