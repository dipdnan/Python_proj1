package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.{col, when, _}
import org.apache.spark.sql.functions.{col, desc, sum, when};

object question_1 {

def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("Deepak Nanaware").master("local[*]").getOrCreate()
  import ss.implicits._
/* 1)Given a DataFrame df containing employee data with columns employee_id, first_name,
and last_name, filter and display the rows where the last_name is "Smith."

2)Given a DataFrame df containing sales data with columns product_id, quantity, and price,
filter and display the rows where the quantity is greater than 10.  */
  val df = List(
    (1, "Deepak","Nanaware"),
    (2, "vijay", "Nanaware"),
    (3, "Parmesh", "Salunke"))
    .toDF("emp_id","first_nm","last_nm")

  val df2 = List(
    (1, 85, 100),
    (2, 20, 200),
    (3, 30, 300),
    (4, 10, 400),
    (5, 10, 200),
    (6, 5, 250),
    (7, 110, 500)).toDF("product_id", "quantity","price")

    //df.filter(col("Last_nm").contains("Nanaware")).show()
    df.filter(col("last_nm")=== "Nanaware").show()

//  val aa = df.select(when(col("last_nm")==="Nanaware","present").as("Last_Name"))
//  aa.show()

//   df2.filter(col("quantity") > 10).show()

  }
}
