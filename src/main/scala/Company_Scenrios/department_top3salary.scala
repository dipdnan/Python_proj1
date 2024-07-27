package Company_Scenrios
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.SparkSession

object department_top3salary {
def main(string:Array[String]):Unit={

  val spark = new SparkSession.Builder()
    .appName("Reading multiple CSV").master("local[*]").getOrCreate()
  import spark.implicits._

  val filepath1 = "C:/Users/Ganesh/Desktop/SRC_Files/emp1.csv"
  val filepath2 = "C:/Users/Ganesh/Desktop/SRC_Files/dept1.csv"

  val input1 = spark.read
     .option("header",true)
      .csv(filepath1)
  val input2 = spark.read
    .option("header",true)
    .csv(filepath2)

  /*with cte as
    (select
      b.name as department
  , a.name as employee
  , a.salary
  , dense_rank() over(partition by a.departmentId order by a.salary desc) rnk
  from employee a
  join department b
  on a.departmentId = b.id)

  select department, employee, salary
  from cte
    where rnk<=3   */

  val windowSpec = Window.partitionBy("dept_id").orderBy(col("salary").desc)


}
}
