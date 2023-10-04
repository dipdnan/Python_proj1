import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, lead,lag}

object Lag_Lead_Demo_prob1 {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()

//  val schema = "product String , date Date, revenue Int "
  val data = spark.read
    .format("csv")
    .option("header", true)
    .option("Inferschema",true)
//     .schema(schema)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_lag_lead_prob1.csv")
    .load()

   data.show()

   val windowSpec = Window.partitionBy("category").orderBy("product")

//   val df= data.withColumn("Lag", col("revenue")-lag("revenue" ,1).over(windowSpec))

   val df1 = data.withColumn("Difference_Sales_Lag",col("sales")- lag("sales",1).over(windowSpec))

   val df2 = data.withColumn("Next_date_sales_Lead",col("sales")+lead("sales",1).over(windowSpec))
//   df.show()

  df1.show()
  df2.show()


}
}
