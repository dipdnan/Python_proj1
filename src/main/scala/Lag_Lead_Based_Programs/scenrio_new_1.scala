package Lag_Lead_Based_Programs

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, lag, lead, rank}

object scenrio_new_1 {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()
  import spark.implicits._

  val df = List(
    (1,"KitKat", 1000.0, "2021 - 01 - 01"),
    (1,"KitKat", 2000.0,"2021 - 01 - 02"),
    (1,"KitKat", 1000.0,"2021 - 01 - 03"),
    (1,"KitKat", 2000.0,"2021 - 01 - 04"),
    (1,"KitKat", 3000.0,"2021 - 01 - 05"),
    (1,"KitKat", 1000.0,"2021 - 01 - 06"))
    .toDF("IT_ID","IT_Name", "Price", "PriceDate")

  val windowSpec = Window.partitionBy("IT_ID","IT_Name").orderBy("PriceDate")

  val dfcol = df.withColumn("Lag",col("Price")-lag(col("Price"),1).over(windowSpec))

  dfcol.show()
}
}
