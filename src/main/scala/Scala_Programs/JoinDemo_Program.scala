package Scala_Programs

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level,Logger}

object JoinDemo_Program {
  def main(args:Array[String]):Unit={

    Logger.getLogger("org").setLevel(Level.OFF)
    // Create a SparkSession
    val spark = SparkSession.builder.appName("JoinExample").master("local[*]").getOrCreate()

    val df1schema = "id Int,name String,age Int";
    val df2schema = "id Int,name String,age Int,city String"

    val df1 = spark.read
      .format("csv")
      .option("header", true)
      .schema(df1schema)
      .option("path", "C:/Users/Ganesh/Downloads/src_files/demo.csv")
      .load()

    val df2 = spark.read
      .format("csv")
      .option("header", true)
      .schema(df2schema)
      .option("path", "C:/Users/Ganesh/Downloads/src_files/input.csv")
      .load()


    df1.printSchema()
    df2.show()
    val conditionType = "inner"

    val condition = df1.col("id") === df2.col("id")


    val joinedDf = df1.join(df2, condition, conditionType)

    joinedDf.show()


    scala.io.StdIn.readLine()
  }
}
