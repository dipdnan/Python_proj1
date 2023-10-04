package Groupby_Problems

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{asc, avg, col, sum}

import scala.reflect.internal.util.TriState.False
object scenrio_2 {
  def main(args:Array[String]):Unit={

    import org.apache.log4j.{Level, Logger}
    Logger.getLogger("org").setLevel(Level.OFF)

    val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()
    import spark.implicits._

    val data = List(
      (1, "ProductA", "Electronics", 1000.0),
      (2, "ProductB", "Clothing", 500.0),
      (3, "ProductC", "Electronics", 800.0),
      (4, "ProductD", "Clothing", 300.0),
      (5, "ProductE", "Electronics", 1200.0)
    ).toDF("id","p_name","category","sales")

     data
       .groupBy("category")
       .agg(avg("sales").alias("Average_sales"))
       .orderBy(col("Average_sales").desc).show()
  }

}
