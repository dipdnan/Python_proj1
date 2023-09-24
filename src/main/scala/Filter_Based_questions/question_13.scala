package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object question_13 {
def main(args:Array[String]):Unit={

  /*13)You have two DataFrames, df1 and df2, each containing columns user_id and
  registration_date. Filter and display the rows from df1 where user_id exists in df2 and the
  registration_date in df1 is before the registration_date in df2.*/

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
