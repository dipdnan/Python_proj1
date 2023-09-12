package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
object question_6 {
def main(args:Array[String]):Unit={

  /*6)Given a DataFrame df, use SQL-like expressions to filter and display the rows where the
  age is between 25 and 40 (inclusive).*/

  val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
  import ss.implicits._


}
}
