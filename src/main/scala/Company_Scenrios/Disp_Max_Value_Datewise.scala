package Company_Scenrios
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, lag, lead, rank,max,window}
import org.apache.spark.sql.types._
import org.apache.spark.sql.types.{StructType,StructField}
object Disp_Max_Value_Datewise {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("WebUI").master("local[*]").getOrCreate()
  import spark.implicits._

  val data = spark.read
    .format("csv")
    .option("Header",true)
    .option("inferSchema",true)
    .option("path","C:/Users/Ganesh/Downloads/src_files/personal_transactions.csv")
    .load()

//  val max_dt = data.groupBy("Customer_No").agg(max("Date")
//    .alias("Date"))

//  val join_df = data.join(max_dt,Seq("Customer_No","Date"),"inner")

  val max_amt = data.groupBy("Customer_No")
    .agg(max("Amount").desc(col("Date")))

  max_amt.show()

 scala.io.StdIn.readLine()

}
}
