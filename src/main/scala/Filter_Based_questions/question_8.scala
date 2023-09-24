package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{avg, col, mean, sequence, sum, to_timestamp, when}

/*8)Given a DataFrame df containing employee data with columns employee_id, salary, and
department, filter and display the rows where the salary is greater than the average salary
of employees in the "Engineering" department.*/
object question_8 {
  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata_Developer").master("local[*]").getOrCreate()
    import ss.implicits._
    val df = Seq(
      (1, 1000, "Engineering"),
      (2, 2000, "Engineering"),
      (3, 3000, "Sales"),
      (4, 2000, "Engineering"),
      (5, 1000, "Sales")
    ).toDF("employee_id", "salary", "department")
    val average_df = df.filter(col("department") === "Engineering").agg(avg(col("salary")))
      .head()
      .getDouble(0)
    // Filter and display rows where salary is greater than the average salary in "Engineering"
    val resultDF = df.filter(col("salary") > average_df)

    // Show the result
    resultDF.show()
  }
}
