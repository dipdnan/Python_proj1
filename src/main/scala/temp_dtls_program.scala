import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{avg, col, desc, sum}

object temp_dtls_program {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()

  val data = ss.read
    .format("csv")
    .option("header", true)
    .option("Inferschema",true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_temp_dtls.csv")
    .load()



   val temp_data_region = Window.partitionBy("Location").orderBy(desc("Temperature"))

   val avg_temp = data.withColumn("temp_Rank",avg("Temperature").over(temp_data_region))

  avg_temp.show()
}
}
