import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, desc, rank, sum, when, window};
object Date_Converted_Program {
def main(args:Array[String]):Unit={

  import org.apache.spark.sql.functions._

  val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
  import ss.implicits._

  val df = List(("2023-10-07", "2023-10-10")).toDF("date1", "date2")

  val df_dff = df.withColumn("date_diff",datediff(col("date2"),col("date1")))
  df_dff.show()
}
}
