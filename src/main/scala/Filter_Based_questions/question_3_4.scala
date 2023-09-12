package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col;

object question_3_4 {

def main(args:Array[String]):Unit={

/*3)Given a DataFrame df containing customer data with columns age and income, filter and
    display the rows where the age is greater than 30 and the income is greater than $50,000.  */

/*4)Given a DataFrame df containing transaction data with columns transaction_id,
  product_id, and quantity, filter and display the rows where the product_id is in the list of
  products to track (e.g., [101, 102, 103]). Use both the filter and where methods for filtering. */

  val ss = new SparkSession.Builder().appName("Deepak Nanaware").master("local[*]").getOrCreate()
  import ss.implicits._

  val df = List(
    (25,35800),
    (45,98000),
    (34,70000)).toDF("age","income")

  val df2 = List(
    (1, 85, 100),
    (2, 20, 200),
    (3, 30, 300),
    (4, 10, 400),
    (5, 10, 200),
    (6, 5, 250),
    (7, 110, 500)).toDF("product_id", "quantity","price")

df.filter(col("age") > 30 && col("income") > 50000).show()



  }
}
