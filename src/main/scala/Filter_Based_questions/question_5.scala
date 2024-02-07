package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc}

object question_5 {

  def main(args:Array[String]):Unit={
/*5)Given a DataFrame df containing customer data with a column email, filter and display the
rows where the email ends with "@example.com."
Given a DataFrame df containing product data with columns product_id and product_name,
filter and display the rows where the product_name is not null.*/

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
    df.orderBy(desc("p_id")).take(2)
  }
}
