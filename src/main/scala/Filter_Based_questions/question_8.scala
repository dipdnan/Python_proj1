package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, sequence, sum, to_timestamp, when}
object question_8 {
  def main(args:Array[String]):Unit={

/*8)Given a DataFrame df containing employee data with columns employee_id, salary, and
department, filter and display the rows where the salary is greater than the average salary
of employees in the "Engineering" department.*/

    val ss = new SparkSession.Builder().appName("Bigdata_Developer").master("local[*]").getOrCreate()
    import ss.implicits._

    val df = List(
      (1, 70000, "dev"),
      (2, 79000, "dev"),
      (2, 30000, "hr"),
      (4, 55000, "Engineering"),(5, 65000, "Engineering")).toDF("employee_id","salary","department")


      df.filter(col("salary") > avg(sum("salary"))).show()







  }
}
