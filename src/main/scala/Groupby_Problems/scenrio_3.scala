package Groupby_Problems

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, max, sum}

object scenrio_3 {
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

     val totalSalesByCategory  = data
       .groupBy("category")
       .agg(sum("sales").alias("total_sales"))

     val maxtotsales = totalSalesByCategory.agg(max("total_sales")).collect()(0)(0).asInstanceOf[Double]
     println("categorywise sales display as below: ")
     totalSalesByCategory.show()

     val resultdf = totalSalesByCategory.filter(col("total_sales")=== maxtotsales)
     println("Highest total sales as below: ")
     resultdf.show()

  }

}
