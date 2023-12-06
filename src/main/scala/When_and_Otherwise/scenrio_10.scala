package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, when};

object scenrio_10 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = Seq(
        (25, 50000),
        (75, 15000),
        (40, 0),
        (30, 15000),
        (25, 10000),
        (80, 8000),
        (50, 10000))
      val df = data.toDF("age", "income")

    val aa = df.withColumn("customer_type",

         when(col("age") < 25 && col("income") >=50000 ,"Young High-Income")

         .when(col("age") > 25 && col("age") <=60 && col("income") >=50000,
           "Middle-Aged High-Income")

         .when(col("age") > 60 && col("income") >=50000,"Senior High-Income")

         .otherwise("Other"))

    aa.show()

  }
}
