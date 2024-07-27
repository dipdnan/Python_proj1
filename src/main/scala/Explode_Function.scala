import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.explode

object Explode_Function {
def main(args:Array[String]):Unit={

  val spark = SparkSession.builder()
    .appName("Explode Array Columns")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  // Sample data
  val data = Seq(
    (1, "John", "Doe", 101, Seq("cricket", "hockey", "kabbadi")),
    (2, "Jane", "Smith", 102, Seq("volleyball", "hockey"))
  )

  // Create DataFrame
  val df = data.toDF("emp_id", "first_name", "last_name", "dept_id", "hobbies")

  // Show original DataFrame
  df.show(truncate = false)

  // Explode hobbies column
  val explodedDF = df.withColumn("hobby", explode($"hobbies")).drop("hobbies")

  // Show the result
  explodedDF.show(truncate = false)

  // Stop SparkSession
  spark.stop()
}
}
/*  Output :-
+------+----------+---------+-------+----------+
|emp_id|first_name|last_name|dept_id|hobby     |
+------+----------+---------+-------+----------+
  |1     |John      |Doe      |101    |cricket   |
  |1     |John      |Doe      |101    |hockey    |
  |1     |John      |Doe      |101    |kabbadi   |
  |2     |Jane      |Smith    |102    |volleyball|
  |2     |Jane      |Smith    |102    |hockey    |
  +------+----------+---------+-------+----------+ */


