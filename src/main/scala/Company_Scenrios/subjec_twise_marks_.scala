package Company_Scenrios
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, rank}
object subjec_twise_marks_ {
def main(args:Array[String]):Unit={
val spark = new SparkSession.Builder().appName("webui")
  .master("local[*]").getOrCreate()
  import spark.implicits._

  val inputData=Seq(
  (1, "Math", 90),(1, "Science", 93),(1, "History", 85),(2, "Math", 85),(2, "Science", 79),
  (2, "History", 90),(3, "Math", 85),(3, "Science", 87),(3, "History", 77),(4, "Math", 78),
  (4, "Science", 91),(4, "History", 80)).toDF("subject_id","sub_name","marks")

  val windowSpec = Window.partitionBy("sub_name")
    .orderBy(desc("marks"))

  val rnk_data = inputData.withColumn("rank_data",rank()over(windowSpec))
  val filter_data = rnk_data.filter(col("rank_data")===1)
    filter_data.show()  }}