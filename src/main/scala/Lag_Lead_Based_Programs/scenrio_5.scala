package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, sum}
//import org.apache.log4j.{Logger,Level}

object scenrio_5 {
def main(args:Array[String]):Unit={

  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()

    val data = spark.read
    .format("csv")
    .option("header", true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_3.csv")
    .load()

     val windowSpec = Window.orderBy("id").rowsBetween(-2,0)

     val resultDF = data.withColumn("rolling_sum",
         sum("salary").over(windowSpec))
     resultDF.show()

}}
