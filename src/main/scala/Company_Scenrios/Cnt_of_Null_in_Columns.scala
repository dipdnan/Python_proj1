package Company_Scenrios
import org.apache.spark.sql.{SparkSession,Row}
import org.apache.spark.sql.types.{StructType,IntegerType,StringType}
import org.apache.spark.sql.functions.{col,count, when}
import org.apache.log4j.{Level,Logger}
object Cnt_of_Null_in_Columns {
def main(args:Array[String]):Unit={

  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = new SparkSession.Builder().appName("WebUI").master("local[*]").getOrCreate()
  import spark.implicits._

  val data = Seq(
    Row(1, "deepak", null),
    Row(null, "abc", 34),
    Row(2, null, null),
    Row(4, "xyz", null)
  )
  val schema1 = new StructType()
    .add("id",IntegerType)
    .add("name",StringType)
    .add("age",IntegerType)

  val df = spark.createDataFrame(spark.sparkContext.parallelize(data),schema1)

  val null_cnt_id = df.select(count(when(col("id").isNull, 1))).as("id_null_cnt").first().getLong(0)
  val null_cnt_name = df.select(count(when(col("name").isNull,1))).as("name_null_cnt").first().getLong(0)
  val null_cnt_age = df.select(count(when(col("age").isNull,1))).as("age_null_cnt").first().getLong(0)

  println(s"the count of null in id column: $null_cnt_id")
  println(s"the count of null in id column: $null_cnt_name")
  println(s"the count of null in id column: $null_cnt_age")

  val resultdf = Seq(
    ("id",null_cnt_id),
      ("name",null_cnt_name),
    ("age",null_cnt_age)
  ).toDF("columns","null_count")
  resultdf.show()

}}
