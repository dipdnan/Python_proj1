package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, desc, lag, lead, when}
//import org.apache.log4j.{Logger,Level}

object scenrio_8 {
def main(args:Array[String]):Unit={

  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()

    val data = spark.read
    .format("csv")
    .option("header", true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_3.csv")
    .load()

     val fil_data = data.filter(col("salary") > 1500)

     val windowSpec = Window.orderBy("id")

     val resultdf = fil_data
       .withColumn("Lead_of_salary",lead("salary",1).over(windowSpec))
       .withColumn("Lag_of_salary",lag("salary",1).over(windowSpec))

     resultdf.show()

}
}
