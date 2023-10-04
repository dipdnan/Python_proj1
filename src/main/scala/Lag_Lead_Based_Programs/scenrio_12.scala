package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.dsl.expressions.{DslExpression, StringToAttributeConversionHelper}
import org.apache.spark.sql.catalyst.expressions.aggregate.Max
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, max, row_number, sum,rank}
//import org.apache.log4j.{Logger,Level}

object scenrio_12 {
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

//     val resultdf = data
//       .withColumn("Max_Salary", max("salary").over(windowSpec))

     data
    .withColumn("row", row_number().over(windowSpec))
    .where(col("row") === 1)
    .drop("row")
    .show()

//     val maxsalarydf = resultdf
//       .filter(col("salary") === col("Max_Salary"))
//       .select("id","name","salary","Max_salary")
//
//     maxsalarydf.show()

    }
}
