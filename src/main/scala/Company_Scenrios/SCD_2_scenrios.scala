package Company_Scenrios
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions
import org.apache.spark.sql.functions.{when,col,coalesce, lit}

object SCD_2_scenrios {
  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("WebUI").master("local[*]").getOrCreate()
    import ss.implicits._

    val empData = Seq(
      (123, 7777777, "Bangalore", "Y"),
      (456, 8888888, "Chennai", "Y"),
      (789, 9999999, "Pune", "Y"),
      (124, 6666666, "Hyderabad", "Y"),
      (333, 9199191, "Delhi", "Y"))
      .toDF("emp_id", "mob_no", "Address", "Flag")

    val empFile = Seq((789, 9999999, "Bangalore"),
      (124, 6666666, "Hyderabad"),
      (333, 9199191, "Bangalore"),
      (444, 7171717, "Jaipur"))
      .toDF("emp_id", "mob_no", "Address")

//    Perform a left-outer join and update the Flag column
      val result = empData.join(empFile, Seq("emp_id", "mob_no", "Address"), "left_outer")
        .withColumn("Flag", when(col("emp_file.emp_id").isNull, "N").otherwise("Y"))
        .select("emp_id", "mob_no", "Address", "Flag")

    // Show the result
    result.show()
  }}
