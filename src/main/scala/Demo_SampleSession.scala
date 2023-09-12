import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object Demo_SampleSession {

  def main(args:Array[String]):Unit={

    Logger.getLogger("org").setLevel(Level.FATAL)

    val ss = new SparkSession.Builder().appName("Welcome to Spark").master("local[4]").getOrCreate()

    val data = ss.read
      .format("csv")
      .option("header",false)
      .option("inferschema",false)
      .option("path","C:/Users/Ganesh/Downloads/sample.csv")
      .load()
      

    data.show(30,false)
    data.printSchema()

    scala.io.StdIn.readLine()
  }
}
