package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, column, desc, lag, lead, rank}
object scenrio_15 {
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

     val lag_sal = data.withColumn("lag_salary",lag("salary",1).over(windowSpec))

     val resultdf = lag_sal
         .withColumn("Lead_of_Salary",lead("salary",1).over(windowSpec))
         .withColumn("incremental_salary",col("salary") > col("lag_salary"))
         .orderBy("id")

     resultdf.show()

    }
}
