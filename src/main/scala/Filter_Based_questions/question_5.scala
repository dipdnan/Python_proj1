package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object question_5 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata_Developer").master("local[*]").getOrCreate()
    import ss.implicits._

    val df_col = List(
      ("dipak.it45@gmail.com"),
      ("ddn111@yahoo.com"),
      ("ddn@gmail.com"),
      ("psalunke@rediff.com")).toDF("email")

    df_col.filter(col("email").endsWith("@gmail.com")).show()
  }
}
