package Scala_Programs

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object avg_score_demo {
  def main(args:Array[String]):Unit={
/*Finding the average score for each subject and the maximum score for each student.*/

    Logger.getLogger("org").setLevel(Level.OFF)
    val spark = SparkSession.builder()
      .appName("OrderDataExample")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val scoreData = List(
      ("Alice", "Math", 80),
      ("Bob", "Math", 90),
      ("Alice", "Science", 70),
      ("Bob", "Science", 85),
      ("Alice", "English", 75),
      ("Bob", "English", 95)
    ).toDF("Student", "Subject", "Score")

    val avg_score = scoreData.groupBy("Subject")
      .agg(avg("Score").alias("Average_Socre"))

    val max_score = scoreData.groupBy("Student")
      .agg(max("Score").alias("Max_Score"))

    avg_score.show()
    max_score.show()

    /*IF score>=90 print distinction
    if score is >75 && <90 then he is firstclass
    if <75 then put him under second class*/

    val aa = scoreData.withColumn("Grade",
      when(col("Score")>=90,"distinction")
    .when(col("Score") >=75 && col("Score") < 90 , "First Class")
    .when(col("Score") <75 , "Second Class")
    .otherwise("Failed")).show()
  }
}
