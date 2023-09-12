package Filter_Based_questions

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, sequence, to_timestamp, when}
object question_7 {

def main(args:Array[String]):Unit={

/*7)Given a DataFrame df containing event data with a column event_timestamp, filter and
display the rows where the event_timestamp is on or after a specific date
(e.g., "2023-01-01") */

  val ss = new SparkSession.Builder().appName("Bigdata").master("local[*]").getOrCreate()
  import ss.implicits._

  val df = Seq("2023-09-12","2023-06-07","2022-11-20","2021-15-08").toDF("event_timestamp")

  val specificdate = "2023-01-01"

  val newdate = df.withColumn("event_timestamp",to_timestamp(col("event_timestamp"),"yyyy-MM-dd"))

  val exact_dt = newdate.filter(col("event_timestamp") >= specificdate)
  exact_dt.show()


}
}
