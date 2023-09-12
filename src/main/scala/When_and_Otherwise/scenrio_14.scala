package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, sum, when};

object scenrio_14 {

  def main(args:Array[String]):Unit={

        /*  14)Given a DataFrame df with columns product_id, quantity, and price, calculate the total revenue for each product.
        However, apply different pricing rules based on the product_id:
        If product_id is in the range 1 to 100 (inclusive), calculate revenue as quantity * price.
        If product_id is in the range 101 to 200 (inclusive), calculate revenue as quantity * price * 0.9 (apply a 10% discount).
        If product_id is in the range 201 to 300 (inclusive), calculate revenue as quantity * price * 0.8 (apply a 20% discount).
        For all other product_id values, set revenue to 0.
        Handling Date and Time Data:  */
    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = List(
        (10, 10,50),
        (82, 5,50),
        (138,10,65),
        (172,5,59),
        (245,5,60),
        (150,25,38),
        (262,22,28)).toDF("p_id","quantity","price")

      val aa = data.withColumn("revenue",
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
