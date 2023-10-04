package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{avg, col, row_number}
//import org.apache.log4j.{Logger,Level}

object scenrio_13 {
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

            // either we can use this code to get output.
   //     val resultdf = data.withColumn("avg_Salary", col("salary")-avg("salary").over(windowSpec))
   //       .orderBy("id")
  //       resultdf.show()

      // 2nd way to achieve the output.
  val avgSalaryColumn = avg(col("salary")).over(windowSpec)

  val resultDF = data
    .withColumn("avgSalary", avgSalaryColumn)
    .withColumn("salaryDifference", col("salary") - col("avgSalary"))
    .orderBy("id")

  resultDF.show()
 }
}
