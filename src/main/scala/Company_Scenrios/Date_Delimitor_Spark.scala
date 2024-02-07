package Company_Scenrios

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{split,col}
object Date_Delimitor_Spark {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("WebUI").master("local[*]").getOrCreate()
  import spark.implicits._

  val data1 = Seq(("A","1–1/1990"),("B","1\2/1990"),("C","1–3/1990"),("D","1\4\1990"))
    .toDF("Name","DOB")

  val result = (
    data1.withColumn("split_DOB", split(col("DOB"), "[-/\\\\/]"))
      .withColumn("Date", col("split_DOB")(0))
      .withColumn("Month", col("split_DOB")(1))
      .withColumn("Year", col("split_DOB")(2))
      .drop("split_DOB")
    )


  result.show()
}}
