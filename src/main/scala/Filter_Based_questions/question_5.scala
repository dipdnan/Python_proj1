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

    val df = Seq((1,"table"),(2,"chair"),(3,null),(4,"mouse")).toDF("p_id","p_name")

    df_col.filter(col("email").endsWith("@gmail.com")).show()

    df.filter(col("p_name").isNotNull).show()
  }
}
