package Company_Scenrios

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions
object aggregate_book_each_student {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("webUI").master("local[*]").getOrCreate()
  import spark.implicits._

  // Define the data for Table_A
  val book_issued = Seq(
    (101, "Mark", "White Tiger"),
    (102, "Ria", "The Fountainhead"),
    (102, "Ria", "The Secret History"),
    (101, "Mark", "Bhagavad Gita"),
    (103, "Loi", "The Fountainhead"))
    .toDF("student_id","student_name","book_issue")

   val aggdf = book_issued.groupBy("student_id","student_name")
     .agg(functions.concat_ws(";",functions.collect_list("book_issue"))
       .alias("book_issued"))

   aggdf.show()

   scala.io.StdIn.readLine()
   
}}
