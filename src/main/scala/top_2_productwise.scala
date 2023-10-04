import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, max, rank, sum}

object top_2_productwise {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()

  val data = ss.read
    .format("csv")
    .option("header", true)
    .option("Inferschema",true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_product.csv")
    .load()



   val sales_data_region = Window.partitionBy("Category").orderBy(desc("SalesAmount"))

   data.withColumn("Rank",rank().over(sales_data_region)).filter(col("Rank")<=2).show()

}
}
