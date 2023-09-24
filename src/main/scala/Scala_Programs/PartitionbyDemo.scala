package Scala_Programs

import org.apache.spark.sql.{SaveMode, SparkSession}

object PartitionbyDemo {
def main(args:Array[String]):Unit={
    // Create a SparkSession

    val spark = SparkSession.builder.appName("OOMExample").master("local[4]").getOrCreate()

    val schema = " name String,age Int,city String"

    val df = spark.read
      .format("csv")
      .option("header", true)
      .schema(schema)
      .option("path", "C:/Users/Ganesh/Downloads/input.csv")
      .load()

    df.show()


    df.write
      .format("csv")
      .mode(SaveMode.Overwrite)
      .bucketBy(4, "city")
      .option("path", "C:/Users/Ganesh/Desktop/output")
      .saveAsTable("mango")


    scala.io.StdIn.readLine()
  }
}
