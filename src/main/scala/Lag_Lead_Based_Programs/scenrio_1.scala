package Lag_Lead_Based_Programs
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, lag, lead, rank}
import org.apache.spark.sql.types._
import org.apache.spark.sql.types.{StructType,StructField}
object scenrio_1 {
  def main(args:Array[String]):Unit={

    val schema11 = "IT_ID Int, IT_Name String, Price Int, PriceDate Date"
    val spark = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()
    import spark.implicits._

//    val schema11 = StructType(
//      Array(
//        StructField("IT_ID", IntegerType),
//        StructField("IT_Name", StringType),
//        StructField("Price", IntegerType),
//        StructField("PriceDate", DateType)
//      ))

    val filepath = "C:/Users/Ganesh/Downloads/src_files/Lag_Lead_Problems/src_1.txt"
    val df = spark.read
       .option("header", true)
      .option("InferSchema", true) // Uncomment this line for schema inference
//      .schema(schema11)
      .option("delimiter", "|")
      .text(filepath)

    val dfWithIntColumn = df
      .withColumn("intColumn", col("IT_ID").cast("int"))
      .withColumn("int_Price",col("Price").cast("double"))

    dfWithIntColumn.show()
  }
}
