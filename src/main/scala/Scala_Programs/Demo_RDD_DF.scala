package Scala_Programs

import org.apache.spark.sql.{SparkSession, Row}
import org.apache.spark.sql.types.{StructType, StructField, IntegerType, StringType}

object Demo_RDD_DF {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Bigdata")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    val schema11 = StructType(
      Array(
        StructField("rollno", IntegerType, true),
        StructField("name", StringType, true),
        StructField("marks", IntegerType, true)
      )
    )

    val rdd = spark.sparkContext.parallelize(Seq(
      Row(1, "deepak", 25),
      Row(2, "mahadu", 27),
      Row(3, "vijay", 28)
    ))

    rdd.collect.foreach(println)

    val empdf = spark.createDataFrame(rdd, schema11)
    empdf.printSchema()

    empdf.show()

    spark.stop()
  }
}

