package Company_Scenrios
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.monotonically_increasing_id
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}
object FirstListOriginalOrder_2ndListReverseOrder {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder()
    .appName("webUI")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

   val list1 = List(12, 25, 31, 20, 18)
   val list2 = List(11, 9, 43, 22, 55)

  // Create a DataFrame with two columns
  val schema = new StructType().add("List1", IntegerType).add("List2", IntegerType)

  val data = list1.zip(list2.reverse).map { case (value1, value2) => Row(value1, value2) }

  val df = spark.createDataFrame(spark.sparkContext.parallelize(data), schema)


  df.show(false)
}
}
