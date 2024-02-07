package Company_Scenrios

import org.apache.spark.sql.SparkSession

object count_non_empty_lines {
def main(args:Array[String]):Unit={

  val spark = SparkSession.builder()
    .appName("count_non_empty_lines")
    .master("local[*]")
    .getOrCreate()

  val path = "C:/Users/Ganesh/Downloads/src_files/src_ff_delimetor.txt"
  val inputData = spark.sparkContext.textFile(path)

  val nonemptyLines = inputData.filter(_.trim.nonEmpty).count()

  println(s"Number of non-empty lines in the file: " + nonemptyLines)

}
}
