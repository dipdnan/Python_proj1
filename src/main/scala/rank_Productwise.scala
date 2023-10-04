import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.expressions.RowNumber
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, rank}

object rank_Productwise {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()

  val data = ss.read
    .format("csv")
    .option("header", true)
    .option("Inferschema",true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_product1.csv")
    .load()



   val std_dtl = Window.partitionBy( "Category").orderBy("SalesAmount")

   val std_show = data.withColumn("Ties_Rank",dense_rank().over(std_dtl)).filter(col("Ties_Rank")===2)

    std_show.show()
}
}
