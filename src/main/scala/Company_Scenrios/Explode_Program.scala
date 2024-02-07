package Company_Scenrios

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.explode
object Explode_Program {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("Explode the data").master("local").getOrCreate()
  import ss.implicits._

  val data = Seq(("abc", 123,Seq("p", "q", "r"))).toDF("col1","col2","col3")

  val df = data.select($"col1",$"col2",explode($"col3").alias("Exploded the data"))

  df.show()

  scala.io.StdIn.readLine()
}
}
