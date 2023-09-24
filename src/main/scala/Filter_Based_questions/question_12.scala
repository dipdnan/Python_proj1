package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.{avg, col, rank, sequence, sum, to_timestamp, when}
import org.apache.log4j.{Logger,Level}

object question_12 {
def main(args:Array[String]):Unit={

  /*12)Given a DataFrame df containing customer data with columns customer_id,
  transaction_id, purchase_amount, and timestamp, filter and display the customers who
  have made at least 3 transactions, and for each of these customers, calculate their total
  purchase amount.*/

  Logger.getLogger("org").setLevel(Level.OFF)
  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import ss.implicits._

  val df = List(
    (1,10,5,"2023-01-31"),(1,20,8,"2023-02-10"),(3,15,9,"2023-02-25"),(4,1,2,"2023-10-10"),(3,11,10,"2023-11-20"),(9,55,33,"2023-06-07"),
    (1,10,3,"2023-10-12"),(3,2,1,"2023-11-20")).toDF("customer_id","transaction_id","purchase_amount","timestamp")

    val sum_df= df.groupBy("customer_id").agg(count("transaction_id").alias("transaction_count"))
    sum_df.show()

    val cust_at_3Tract = sum_df.filter($"transaction_count" >=3)
    cust_at_3Tract.show()


    val result = cust_at_3Tract
      .join(df,Seq("customer_id"),"inner")
      .groupBy("customer_id")
      .agg(sum("purchase_amount").alias("total_purchase_amount"))
    result.show()






}
}
