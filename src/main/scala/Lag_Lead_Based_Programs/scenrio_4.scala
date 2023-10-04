package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead}
//import org.apache.log4j.{Logger,Level}

object scenrio_4 {
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

//      val dfwithDiff = data.withColumn("Lag_SalaryDiff",col("salary")-lag("SALARY",1).over(windowSpec))

       val selcol = data.select(
                col("id"),
                col("salary"),
                ((col("salary") - lag("salary", 1).over(windowSpec)) / lag("salary", 1)
                  .over(windowSpec) * 100).alias("percentage_change"))
       selcol.show()

       val resultDF = data.withColumn("previous_salary", lag("salary", 1).over(windowSpec))
            .withColumn("percentage_change",
           (col("salary") - col("previous_salary")) / col("previous_salary") * 100)
       resultDF.show()


}
}
