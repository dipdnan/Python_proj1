import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.{col, when, _}

package When_and_Otherwise
{
object scenior_1 {
  def main(args: Array[String]): Unit = {

    val ss = new SparkSession.Builder().appName("Bigdata_Developer")
      .master("local[*]").getOrCreate()
    import ss.implicits._

    //    val aa = Seq(("Deepak",35),("Akshay",29),("Vijay",25),("Nitin",31),("Indrajeet",30)).toDF("Name","Age")

    val ab = Seq((25, "Male"), (16, "Male"), (10, "Female"), (21, "Male"),
      (18, "Female")).toDF("age", "gender")

    val new_col = ab.withColumn("is_adult",
      when(col("age") >= 18, "TRUE")
        .otherwise("FALSE"))

    new_col.show()

    scala.io.StdIn.readLine()
  }
  }
}
/* Output :
+---+------+--------+
|age|gender|is_adult|
+---+------+--------+
| 25|  Male|    TRUE|
| 16|  Male|   FALSE|
| 10|Female|   FALSE|
| 21|  Male|    TRUE|
| 18|Female|    TRUE|
+---+------+--------+
 */