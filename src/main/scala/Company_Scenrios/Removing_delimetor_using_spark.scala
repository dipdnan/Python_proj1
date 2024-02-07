package Company_Scenrios
import org.apache.spark.sql.{SparkSession, functions}
import org.apache.spark.sql.functions.{col, split}
//org.apache.spark.sql.Column

object Removing_delimetor_using_spark {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("WebUI").master("local[*]").getOrCreate()
  import spark.implicits._


   val inputData = Seq(
     "nanaware,deepak~|36",
      "patole,sachin ~|38",
       "maindad,nitin ~|34",
       "abc,cde ~|23",
       "xyz,abc ~|90")

   val rdd =spark.sparkContext.parallelize(inputData)
  val splitRDD = rdd.map(line => {
    val Array(name, age) = line.split("~\\|")
    (name, age)
  })

  // Convert RDD to DataFrame for better display
  import spark.implicits._
  val df = splitRDD.toDF("Name", "Age")

  // Show the result
  df.show()

  }
}
