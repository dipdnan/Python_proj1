package Groupby_Problems

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, max, quarter, sum, to_date, when}

object scenrio_9 {
  def main(args:Array[String]):Unit={

    import org.apache.log4j.{Level, Logger}
    Logger.getLogger("org").setLevel(Level.OFF)

    val spark = new SparkSession.Builder()
      .appName("Bigdata")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._

    val data = Seq(
      (1, "ProductA", "Electronics", "2023-01-15", 1000.0),
      (2, "ProductB", "Clothing", "2023-02-20", 500.0),
      (3, "ProductC", "Electronics", "2023-04-10", 800.0),
      (4, "ProductD", "Clothing", "2023-05-05", 300.0),
      (5, "ProductE", "Electronics", "2023-01-25", 1200.0)
    )
      .toDF("id", "p_name", "category", "year", "sales")

    // Group by "category" and "year" and calculate the average sales
    val averageSalesByCategoryAndYear = data.groupBy("category", "year")
      .agg(avg("sales").alias("average_sales"))

    // Add a new column indicating whether the average is above a threshold (e.g., 800)
    val threshold = 800.0
    val result = averageSalesByCategoryAndYear
      .withColumn("above_threshold",
        when(col("average_sales") > threshold, true)
          .otherwise(false))

    // Show the result
    result.show()
  }

}
