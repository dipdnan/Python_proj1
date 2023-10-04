package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead, when}

object scenrio_2 {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()
  import spark.implicits._

      val df = List(
      (1, "John", 1000, "01 / 01 / 2016"),
      (1, "John", 2000, "02 / 01 / 2016"),
      (1, "John", 1000, "03 / 01 / 2016"),
      (1, "John", 2000, "04 / 01 / 2016"),
      (1, "John", 3000, "05 / 01 / 2016"),
      (1, "John", 1000, "06 / 01 / 2016"))
      .toDF("ID","NAME","SALARY","DATE")

//    val data = spark.read
//    .format("csv")
//    .option("header", true)
//    .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_2.csv")
//    .load()

          // Cast "SALARY" column to Double
//        val df = data.withColumn("SALARY", col("SALARY").cast("Double"))

      val windowSpec = Window.orderBy("DATE")

      val dfwithDiff = df.withColumn("SalaryDiff",col("SALARY")-lag("SALARY",1).over(windowSpec))

      val result = dfwithDiff.withColumn("Lag",
                   when(col("SalaryDiff") < 0,"DOWN")
                  .when(col("SalaryDiff") > 0 ,"UP")
                  .otherwise("Unchanged"))

      result.show()



}
}
