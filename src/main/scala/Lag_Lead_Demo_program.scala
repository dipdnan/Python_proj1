import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, lag, lead, rank}
import org.apache.spark.sql.types
import org.apache.log4j.{Logger,Level}
object Lag_Lead_Demo_program {
def main(args:Array[String]):Unit={

  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import spark.implicits._

//  val schema = "product String , date Date, revenue Int "
  val data = spark.read
    .format("csv")
    .option("header", true)
    .option("Inferschema",true)
//     .schema(schema)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_lag_lead.csv")
    .load()

   println("The Original Data SET as given below:---")
   data.show()

   val windowSpec = Window.partitionBy("product").orderBy("date")

   println("The LAG result set as below:------")
   val df= data.withColumn("Lag", col("revenue")-lag("revenue" ,1).over(windowSpec))
   df.show()


   println("The LEAD result set as below:------")
   val df1 = data.withColumn("Lead",col("revenue")+lead("revenue",1).over(windowSpec))
   df1.show()
}
}
