package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, sum, when};

object scenrio_11 {

  def main(args:Array[String]):Unit={

        /*  11)Given a DataFrame df with columns product_id, quantity, and price, calculate the total revenue for each product.
            If product_id is null, quantity is null, price is null, or any of these columns is negative,
            set the revenue to 0. Additionally, if the product_id is null, set it to -1.   */

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = List(
        (1, 10,50000),
        (2, 15,15000),
        (3,11,2000),
        (0,50,15000),
        (5,20,10000),
        (6,25,8000),
        (0,22,10000))
      val df = data.toDF("product_id", "quantity", "price")
    val dfrdd = df.withColumn("revenue",
      col = when(col("product_id").isNull || col("quantity").isNull || col("price").isNull ||
        col("product_id") < 0 || col("quantity") < 0 || col("price") < 0, "0")
        && when(col("product_id").isNull, "-1").otherwise("Other"))

//        .groupby("product_id")
//        .agg(sum("revenue").alias("Total_Revenue"))
//        .orderBy(desc("Total_Revenue")))
    dfrdd.show()





  }
}
