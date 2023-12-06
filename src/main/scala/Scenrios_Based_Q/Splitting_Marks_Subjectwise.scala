package Scenrios_Based_Q

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.split

object Splitting_Marks_Subjectwise {
def main(args:Array[String]):Unit={

 val ss = new SparkSession.Builder()
         .appName("DataEngineer")
         .master("local[*]")
         .getOrCreate()
  import ss.implicits._

  val data = Seq(
    (1,"12##34##56"),
    (2,"23##45##56")).toDF("id","marks")

  val splitmarks = data
    .withColumn("math",split($"marks","##")(0))
    .withColumn("geo",split($"marks","##")(1))
    .withColumn("sci",split($"marks","##")(2))
    .drop("marks")

  splitmarks.show()
  scala.io.StdIn.readLine()

 }
}
