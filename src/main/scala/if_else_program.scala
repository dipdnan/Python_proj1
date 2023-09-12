import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.col

object if_else_program {

  def main(args:Array[String]):Unit={


    val ss = new SparkSession.Builder().appName("Welcome to Spark").master("local[*]").getOrCreate()
    import ss.implicits._
    val df = List(("Alice", 15), ("Bob", 30), ("Charlie", 31)).toDF("name", "age")
//    val categorizedDF = df.withColumn("category", when(col("age") < 30, "Young").otherwise("Old"))

    val categorizedDF = df.withColumn("Age_Group",
       when(col("age") < 18, "Child")
      .when(col("age")>18 and col("age")<=30,"Young" )
      .when(col("age")>30,"adult").otherwise("No Age bar"))
    categorizedDF.show()
  }
}
