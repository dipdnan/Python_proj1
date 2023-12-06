package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, sum, when};

object scenrio_15 {

  def main(args:Array[String]):Unit={

        /*  15)Given a DataFrame df with a column timestamp, create a new column day_period to categorize
        the time of day. Use the when and otherwise functions to categorize timestamps as "Morning,"
        "Afternoon," "Evening," or "Night" based on the hour of the timestamp.
        Combining Multiple DataFrames: */
    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = Seq(("04:00"),("13:00"),("17:00"),("21:00"),("23:00"),("07:00"),("15:00")).toDF("timestamp")

      val aa = data.withColumn("Day_Period",
        when(col("timestamp").between("06:00","11:59"),"Morning")
          .when(col("timestamp").between("12:00","17:00"),"Afternoon")
          .when(col("timestamp").between("17:01","18:59"),"Evening")
          .when(col("timestamp").between("19:00","22:59"),"Night")
          .when(col("timestamp").between("23:00","06:00"),"Mid-Night")
          .otherwise("Unknown"))

         aa.show()
  }
}
