package Company_Scenrios
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.split

object program10_marks_split {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("WebUI").master("local[*]").getOrCreate()
  import ss.implicits._

  val inputData = Seq(
    (1,"12##34##56"),
      (2,"23##45##56")
  ).toDF("Id","Marks")

  val SplitResult = inputData
    .withColumn("marksArray",split($"Marks","##"))
    .withColumn("math",$"marksArray".getItem(0))
    .withColumn("science",$"marksArray".getItem(1))
    .withColumn("history",$"marksArray".getItem(2))
    .drop("marksArray","Marks")

  SplitResult.show()

}
}
