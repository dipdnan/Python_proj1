package Scenrios_Based_Q

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions.{col, initcap, monotonically_increasing_id, when}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object Student_Grade_marks_Spark {
  def main(args:Array[String]):Unit= {

    /*2. Spark code
    Student_1,sub1,sub2,sub3
    Add grade column based on condition
    If sub1+sub2+sub3 <=35 then fail
    If it is >=35 and <50 third class
    If >=50 and <60 second
    Else first
    Create sparksession
    Read from csv
    Write to parquet .*/

    val ss = new SparkSession.Builder().appName("Marks_Wise_Grade").master("local[*]").getOrCreate()
    import ss.implicits._


    val df1schema = "Student_1 String,sub1 Int,sub2 Int,sub3 Int";

    val df1 = ss.read
      .format("csv")
      .option("header", true)
      .schema(df1schema)
      .option("path", "C:/Users/Ganesh/Downloads/src_files/student_marks.csv")
      .load()

    val total = col("sub1")+col("sub2")+col("sub3")
    val aa = df1.select(col("Student_1"),
      when(total <= 35, true).otherwise(false).alias("Failed"),
      when(total >= 35 && total <= 50, true).otherwise(false).alias("Third Class"),
      when(total > 50 && total < 60, true).otherwise(false).alias("Second Class"),
      when(total >= 60, true).otherwise(false).alias("Distinction Class"))

    aa.show()

        df1.write
          .format("parquet")
          .mode(SaveMode.Append)
          .option("path","C:/Users/Ganesh/Downloads/parquet_output")
          .save()

    df1.show()
      }
  }
