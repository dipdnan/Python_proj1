package Company_Scenrios

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, rank, row_number}
import org.apache.log4j.{Level,Logger}
object Second_highest_no_display {
def main(args:Array[String]):Unit={

  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("SparkWebUI").master("local[*]").getOrCreate()
  import spark.implicits._

  val data = List(
    (1, "deepak", 20000, "Pune"),
    (2, "sandip", 50000, "hyd"),
    (3, "munna", 90000, "mumbai"),
    (4, "tara", 70000, "Chennai"),
    (5, "sachin", 30000, "bengaluru")
  ).toDF("id","name","salary","address")
  data.show()

  println("2nd highest number as below: ")
  val windowSpec = Window.orderBy(desc("id"))
  val d_rnk = data.withColumn("dense_rnk", dense_rank() over(windowSpec))
  val d_filt = d_rnk.filter(col("dense_rnk")===2)
  d_filt.show()

  println("2nd least/smallest number as below: ")
  val windowSpec2 = Window.orderBy(col("id"))
  val ro_number = data.withColumn("Row_number",row_number() over(windowSpec2))
  val row_filter = ro_number.filter(col("Row_number")===2)
  row_filter.show()

  scala.io.StdIn.readLine()

}}
