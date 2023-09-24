package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.{avg, col, rank, sequence, sum, to_timestamp, when}
object question_9 {
def main(args:Array[String]):Unit={
/*9)Given a DataFrame df containing sales data with columns order_id, product_id,
order_date, and quantity, filter and display the rows where the quantity is greater than the
average quantity for the corresponding product_id over a specified window of time (e.g., the
last 7 days). Use window functions for this task.*/

  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import ss.implicits._

  val data = Seq(
    (1, 101, "2023-09-01 10:00:00", 5),
    (2, 101, "2023-09-02 11:00:00", 6),
    (3, 102, "2023-09-03 12:00:00", 7),
    (4, 102, "2023-09-05 14:00:00", 8),
    (5, 101, "2023-09-07 16:00:00", 7)
  ).toDF("order_id","product_id","order_date","quantity")

  val windowSpec = Window.partitionBy("product_id").orderBy("order_date")


  val avgQuantityLast7Days = avg(col("quantity")).over(windowSpec.rangeBetween(-6, 0))
  // This defines the 7-day window (0 is the current row)

   val dfWithAvgQuantity = data.withColumn("avg_quantity_last_7_days", avgQuantityLast7Days)

   val result = dfWithAvgQuantity.filter(col("quantity") > col("avg_quantity_last_7_days"))

   result.show()


}
}
