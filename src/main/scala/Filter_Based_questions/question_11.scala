package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.{avg, col, rank, sequence, sum, to_timestamp, when}
object question_11 {
def main(args:Array[String]):Unit={
  /*11)Given a DataFrame df containing transaction data with columns transaction_id,
  product_id, quantity, and timestamp, filter and display the rows where the quantity is
  greater than 5 and the timestamp is within a specified time range (e.g., between "2023-01-
  01 00:00:00" and "2023-02-28 23:59:59")*/
  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import ss.implicits._

  val df = List(
    (1,10,5,"2023-01-31"),(2,20,8,"2023-02-10"),(3,15,15,"2023-02-25"),(4,1,2,"2023-10-10"),
    (5,10,3,"2023-10-12")).toDF("transaction_id","product_id","quantity","timestamp")

  val aa = df.withColumn("range_btw_given_dt",
    when(col("quantity") >=5 && col("timestamp").between
    (lit("2023-01-01"),lit("2023-02-28")),"true").otherwise("false"))

  aa.show()
}
}
