package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, row_number, sum, when}
//import org.apache.log4j.{Logger,Level}

object scenrio_11 {
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
       .withColumn("Total_Salary", sum("salary").over(windowSpec))

//     val filterdata = resultdf.filter(row_number().over(windowSpec)===1)
//       .dropDuplicates("name")
//       .drop("Total_Salary")

     resultdf.show()
//     filterdata.show()
    }
}
