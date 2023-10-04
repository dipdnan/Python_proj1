package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead}

object scenrio_17 {
def main(args:Array[String]):Unit={

  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()

     val data = spark.read
     .format("csv")
     .option("header", true)
     .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_3.csv")
     .load()

     val windowSpec = Window.partitionBy("name").orderBy("id")

     val resultdf = data
         .withColumn("Prev_salary",lag("salary",1).over(windowSpec))
         .withColumn("percentage_change",
           (col("salary") - col("Prev_salary"))/col("Prev_salary")*100)
         .orderBy("id")
     resultdf.show()

    }
}
