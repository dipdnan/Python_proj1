package Company_Scenrios

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, lit, when}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StringType
object Remove_Spcial_cha_from_SRC {
def main(args:Array[String]):Unit= {

  val spark = new SparkSession.Builder().appName("webUI").master("local[*]").getOrCreate()

  val df = spark.read
    .format("csv")
    .option("header", true)
    .option("inferSchema", false)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_emp_sp_char.csv")
    .load()

  val nullValues = Seq("$", "#", "&", "NA")

  // Replace specified null values with None
//  val cleanedData = data.columns.foldLeft(data) {
//    (tempDF, col) =>
//      nullValues.foldLeft(tempDF) {
//        (currentDF, nullValue) =>
//          currentDF.withColumn(col, when(col(col).equalTo(nullValue), lit(null)).otherwise(col(col)))
//      }
//  }

  // Replace special characters with null for string columns
  val dfCleaned = df
    .withColumn("first", when(col("first").isin("#", "$", "&", "NA"), lit(null))
      .otherwise(col("first")))
    .withColumn("last", when(col("last").isin("#", "$", "&", "NA"), lit(null))
      .otherwise(col("last")))
    .withColumn("age", when(col("age").isin("#", "$", "&", "NA"), lit(null))
      .otherwise(col("age")))
    .withColumn("salary", when(col("salary").isin("#", "$", "&", "NA"), lit(null))
      .otherwise(col("salary")))

  dfCleaned.show(false)

}
}
