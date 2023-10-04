import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions.{sum}
object Total_sales_regionwise {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import ss.implicits._

  val data = ss.read
    .format("csv")
    .option("header", true)
    .option("Inferschema",true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_sales_data.csv")
    .load()

//  val sales_data_region = data.groupBy("region")
//    .agg(sum("salesamount").alias("Sales_DAta"))

   val sales_data_region = Window.partitionBy("region").orderBy("salesamount")

   val win_sum = data.withColumn("total_sales",sum("salesamount").over(sales_data_region))

  win_sum.show()
}
}
