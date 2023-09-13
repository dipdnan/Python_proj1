package Filter_Based_questions
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.{avg, col, rank, sequence, sum, to_timestamp, when}

object question_10 {
def main(args:Array[String]):Unit={
  /*10)Given a DataFrame df containing employee data with columns employee_id, department,
  and salary, filter and display the departments where the average salary of employees in that
  department is greater than $60,000*/

  val ss = new SparkSession.Builder().appName("Bigdata_Developer").master("local[*]").getOrCreate()
  import ss.implicits._

  val df = Seq(
    (1,10,58000),
    (2,10,88000),
    (3,20,98000),
    (4,30,48000),(5,30,35000),(6,50,66000),(7,50,75000),(8,40,67000)).toDF("employee_id","department","salary")

  val avgdfsal = df.groupBy("department").agg(avg("salary").alias("avg_salary"))

  val bigsal = avgdfsal.filter(col("avg_salary") > 60000).orderBy(desc("avg_salary"))
  bigsal.show()



}
}
