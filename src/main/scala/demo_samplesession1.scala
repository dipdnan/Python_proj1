import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

import org.apache.spark.sql.{SaveMode, SparkSession}

object demo_samplesession1 {

  def main(args:Array[String]):Unit={

    val spark = new SparkSession.Builder().appName("Spark").master("local[*]").getOrCreate()


    val data = spark.read    // actions
      .format("csv")
      .option("header", true)
      .option("inferschema", true)  // actions
      .option("path", "C:/Users/Ganesh/Desktop/Data.csv")
      .load()



    data.write
      .format("csv")
      .mode(SaveMode.Overwrite)
      .option("path","C:/Users/Ganesh/Documents/output1")
      .save()

    data.show()


    scala.io.StdIn.readLine()
  }
}
