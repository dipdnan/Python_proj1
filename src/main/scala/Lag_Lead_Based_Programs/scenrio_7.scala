package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, column, desc, lag, lead, min}
//import org.apache.log4j.{Logger,Level}

object scenrio_7 {
def main(args:Array[String]):Unit={

  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()

    val data = spark.read
    .format("csv")
    .option("header", true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_3.csv")
    .load()

     val windowSpec = Window.partitionBy("name").orderBy(desc("id"))


     val resultdf = data
       .withColumn("Lead_Salary", lead("salary", 1).over(windowSpec))
       .withColumn("Lag_Salary", lag("salary", 1).over(windowSpec))

     resultdf.show()

}
}
