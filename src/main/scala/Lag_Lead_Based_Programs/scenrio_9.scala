package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead}
//import org.apache.log4j.{Logger,Level}

object scenrio_9 {
def main(args:Array[String]):Unit={

  import org.apache.log4j.{Level, Logger}
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()

    val data = spark.read
    .format("csv")
    .option("header", true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_3.csv")
    .load()

     println("---------------------Given data-----------------------")
     data.show()

   val fildata = data.filter(col("salary")>500)

  // Assuming your DataFrame is named "data"
  val windowSpec = Window.orderBy("id")

  println("salarychange data as below ------------------")
  val dfWithSalaryChange = fildata
    .withColumn("Lag_SalaryChange", lag("SALARY", 1).over(windowSpec))
    .withColumn("Lead_SalaryChange",lead("salary",1).over(windowSpec))


  dfWithSalaryChange.show()

  }
}
