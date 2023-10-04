package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lag, lead, when}
//import org.apache.log4j.{Logger,Level}

object scenrio_3 {
def main(args:Array[String]):Unit={

  import org.apache.log4j.{Logger,Level}
  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()
  import spark.implicits._

    val data = spark.read
    .format("csv")
    .option("header", true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_3.csv")
    .load()


      val windowSpec = Window.orderBy("id")

      val dfwithDiff = data.withColumn("Lag_SalaryDiff",col("salary")-lag("SALARY",1).over(windowSpec))

     val dflead_diff = data.withColumn("Lead_Salarydiff",col("salary")+lead("salary",1).over(windowSpec))

    dfwithDiff.show()
    dflead_diff.show()

}
}
