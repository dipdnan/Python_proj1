package Company_Scenrios

import org.apache.spark.sql.functions
import org.apache.spark.sql.SparkSession

object Associated_Key_Mismatched {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("webUI").master("local[*]").getOrCreate()
  import spark.implicits._

  // Define the data for Table_A
  val tableAData = Seq(
    (1, 123, 7777777, "Bangalore"),
    (2, 456, 8888888, "Chennai"),
    (3, 789, 9999999, "Bangalore"),
    (4, 124, 6666666, "Hyderabad"),
    (5, 333, 9199191, "Bangalore"),
    (6, 444, 7171717, "Jaipur")
  )
  // Define the data for Table_B
  val tableBData = Seq(
    (1, 123, "Data_Eng", 50000),
    (2, 456, "Data_Sci", 60000),
    (3, 789, "Data_Eng", 55000),
    (6, 124, "Data_Eng", 60000),
    (5, 333, "Manager", 80000),
    (4, 444, "Sr.Man", 100000)
  )

  // Create DataFrames for Table_A and Table_B
  val tableA = spark.createDataFrame(tableAData).toDF("key", "emp_id", "mob_no", "add")
  val tableB = spark.createDataFrame(tableBData).toDF("key", "emp_id", "Desig", "Sal")

  // Register the DataFrames as temporary tables
  tableA.createOrReplaceTempView("Table_A")
  tableB.createOrReplaceTempView("Table_B")

  // Write the Spark SQL query to find emp_id with mismatched keys
  val result = spark.sql(
    """
      SELECT DISTINCT a.emp_id
      FROM Table_A a
      JOIN Table_B b ON a.key = b.key
      WHERE a.emp_id <> b.emp_id
    """
  )

  // Show the result
  result.show()

  scala.io.StdIn.readLine()
}}
