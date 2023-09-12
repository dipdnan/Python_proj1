import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object Rename_column_DF {

  def main(args:Array[String]):Unit={


    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

    val df = List(("alice", 25), ("bob", 30), ("charlie", 35),("alice", 25)).toDF("name", "age")
//    val capitalizedDF = df.withColumn("name", initcap(col("name")))

//     val distinctDF = df.dropDuplicates()

    val df2 = df.withColumn("newid",monotonically_increasing_id())
     df2.show()
    df2.sort("age").show()




  }
}
