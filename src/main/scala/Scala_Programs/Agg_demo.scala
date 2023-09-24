package Scala_Programs

import org.apache.spark.sql.SparkSession

object Agg_demo {
def main(args:Array[String]):Unit={

    val spark = SparkSession.builder()
      .appName("OrderDataExample")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val orderData = List(
      ("Order1", "John", 100),
      ("Order2", "Alice", 200),
      ("Order3", "Bob", 150),
      ("Order4", "Alice", 300),
      ("Order5", "Bob", 250),
      ("Order6", "John", 400)
    ).toDF("OrderID", "Customer", "Amount")

    orderData.show()


    import org.apache.spark.sql.functions._

    // Group by Customer and calculate the count of orders
    val orderCountByCustomer = orderData.groupBy("Customer")
      .agg(count("OrderID").alias("OrderCount"))

    // Group by Customer and calculate the sum of Amount
    val totalAmountByCustomer = orderData.groupBy("Customer")
      .agg(sum("Amount").alias("TotalAmount"))

    orderCountByCustomer.show()
    totalAmountByCustomer.show()
  }
}
