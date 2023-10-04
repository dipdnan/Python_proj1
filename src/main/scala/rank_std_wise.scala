import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, dense_rank, desc, rank}

object rank_std_wise {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()

  val data = ss.read
    .format("csv")
    .option("header", true)
    .option("Inferschema",true)
    .option("path", "C:/Users/Ganesh/Downloads/src_files/src_student_dtl.csv")
    .load()



   val std_dtl = Window.partitionBy( "Subject").orderBy(desc("Score"))

   val std_show = data.withColumn("student_rank",rank().over(std_dtl)).filter(col("student_rank")<=1)
  std_show.show()
}
}
