package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, sum, when};

object scenrio_16 {

  def main(args:Array[String]):Unit={

        /* 16)You have two DataFrames, df1 and df2, each containing columns user_id and subscription_date.
        Create a new DataFrame combined_df that contains all rows from both df1 and df2.
        If a user_id exists in both DataFrames, select the subscription date from df2,
        otherwise, select it from df1. Also, add a new column source to indicate whether the data is from df1 or df2.
        Advanced Aggregation and Window Functions:  */
    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val df1 = List(10,15,20,25,25,50,53).toDF("p_id","quantity","price")

      val aa = df1.withColumn("revenue",
        when(col("p_id").between(1,100),col("quantity")*col("price"))
          .when(col("p_id").between(101,200),col("quantity")*col("price")/100*0.9)
          .when(col("p_id").between(201,300),col("quantity")*col("price")/100*0.8)
          .otherwise("0"))

        .groupBy("p_id")
        .agg(sum("revenue").alias("Total_Revenue"))
        .orderBy(desc("Total_Revenue"))
    aa.show()










  }
}
