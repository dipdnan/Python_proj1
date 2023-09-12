package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, sum, when};

object scenrio_6 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = Seq(
        (1, 10, -10),
        (1, 20, -20),
        (3, 30, 300),
        (4, 10, 400),
        (4, 30, 200),
        (6, 20, 250),
        (7, 10, 500))
      val df = data.toDF("product_id", "quantity", "price")

    val dfrdd = df.withColumn("revenue",
      when(col("product_id").isNull || col("quantity")<=0 || col("price")<=0 ,0)
      .otherwise(col("quantity")*col("price")))

      .groupBy("product_id")
      .agg(sum("revenue").alias("Total_Revenue"))
      .orderBy(desc("Total_Revenue"))
       dfrdd.show()

  }
}
