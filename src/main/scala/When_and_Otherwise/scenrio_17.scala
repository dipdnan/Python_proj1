package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, desc, rank, sum, when, window};

object scenrio_17 {

  def main(args:Array[String]):Unit={

        /* 17)Given a DataFrame df with columns employee_id, department, and salary,
        calculate the average salary for each department.
        However, for each department, rank employees based on their salary,and
        create a new column rank_within_department to indicate the employee's rank within their department. */

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = List(
        (1, 10,50),
        (2, 5,50),
        (2,10,10),
        (5,5,15),
        (5,5,30),
        (50,25,15),
        (50,25,15)).toDF("employee_id","department","salary")

      val ab = Window.partitionBy("department").orderBy("salary")
      val aa = data.withColumn("rank_within_department",rank().over(ab))


//        .groupBy("employee_id")
//        .agg(sum("revenue").alias("Total_Revenue"))
//        .orderBy(desc("Total_Revenue")))
    aa.show()
  }
}
