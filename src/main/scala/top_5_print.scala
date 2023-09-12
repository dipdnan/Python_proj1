import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

import org.apache.log4j.{Logger,Level}
import org.apache.log4j.Level

object top_5_print {

  def main(args:Array[String]):Unit={

    Logger.getLogger("org").setLevel(Level.FATAL)

    val sc = new SparkContext("local[*]","Deepak")

    val input = sc.textFile("C:/Users/Ganesh/Desktop/SRC_Files/ipadd.txt");

    val words = input.flatMap(x => x.split((",")))

//    val lowercase = words.map(x => x.toLowerCase())

    val words1 = words.map(x => (x, 1))
//    words1.collect.foreach(println)

    val redresult = words1.reduceByKey((x, y) => x + y)


    val srt = redresult.sortBy(a => a._2,false)

    val t = srt.take(5)

    println("ReducebyKey values are ")
    redresult.foreach(println)  // print reduceByKey values
    println("SortBy values are :")

    srt.collect.foreach(println)  // print sorting the  values

    println("Dispaly the top values using take function")
    t.foreach(println) // print top values


    scala.io.StdIn.readLine()

  }
}
