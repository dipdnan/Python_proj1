package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, sequence, when}

object question_6 {

def main(args:Array[String]):Unit={

  /*6)Given a DataFrame df, use SQL-like expressions to filter and display the rows where the
  age is between 25 and 40 (inclusive).*/

  val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
  import ss.implicits._

  val data = Seq(25,18,21,40,35,29).toDF("age")
//  val a1 = data.withColumn("new_age",
//    when(col("age").between(25,40),"true").otherwise("false"))

  data.filter(col("age").between(25,40)).show()
//  a1.show()




}
}
