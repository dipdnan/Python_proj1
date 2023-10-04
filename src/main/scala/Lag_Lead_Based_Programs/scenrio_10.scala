package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead, sum, when}
//import org.apache.log4j.{Logger,Level}

object scenrio_10 {
def main(args:Array[String]):Unit={

  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()

    val data = spark.read
    .format("csv")
    .option("header", true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_3.csv")
    .load()

    val windowSpec = Window.orderBy("id")

     val resultdf = data
       .withColumn("Lag_of_salary",
         when(lag("name",1).over(windowSpec)=!= col("name"),1).otherwise(0))
       .withColumn("Lag_of_salary",sum("Lag_of_salary").over(windowSpec))

     resultdf.show()
    }
}
