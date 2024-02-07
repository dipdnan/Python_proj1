package Company_Scenrios
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, expr, when}

object positive_to_neg_values {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder()
    .appName("pos_to_negative_values")
    .master("local[*]")
    .getOrCreate()
  import ss.implicits._

  val data = Seq(
      ("1", "ABC", "-20"),
      ("2", "DEF", "40"),
      ("3", "GHI", "50"),
      ("4", "JKL", "1000"),
      ("5", "MNO", "-300")
  )

  val schema = List("id","name","values")

  val df = ss.createDataFrame(data).toDF(schema: _*)

  val code = df.withColumn("values",
    when(col("values") > 0,expr("-values")).otherwise(col("values")))

  code.show()

  scala.io.StdIn.readLine()


}
}
