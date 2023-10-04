package Groupby_Problems

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{max, quarter, sum, to_date}

object scenrio_8 {
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
      .toDF("id","p_name","category","date","sales")

    // Extract the quarter from the "date" column
    val dfWithQuarter = data.withColumn("quarter", quarter(to_date($"date", "yyyy-MM-dd")))

    // Group by "category" and "quarter" and calculate the total sales
    val totalSalesByCategoryAndQuarter = dfWithQuarter.groupBy("category", "quarter")
      .agg(sum("sales").alias("total_sales"))

    // Show the result
    totalSalesByCategoryAndQuarter.show()
  }

}
