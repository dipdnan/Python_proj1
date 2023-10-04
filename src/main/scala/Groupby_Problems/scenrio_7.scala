package Groupby_Problems

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.max

object scenrio_7 {
  def main(args:Array[String]):Unit={

    import org.apache.log4j.{Level, Logger}
    Logger.getLogger("org").setLevel(Level.OFF)

    val spark = new SparkSession.Builder()
      .appName("Bigdata")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._

    val data = Seq(
      (1, "ProductA", "Electronics", 2023, 1000.0),
      (2, "ProductB", "Clothing", 2023, 500.0),
      (3, "ProductC", "Electronics", 2022, 800.0),
      (4, "ProductD", "Clothing", 2022, 300.0),
      (5, "ProductE", "Electronics", 2023, 1200.0)
    ).toDF("id","p_name","category","year","sales")

     data
       .groupBy("category","year")
       .agg(max("sales").alias("Highest_sales"))
       .show()
  }

}
