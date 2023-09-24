package Scala_Programs

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
object Check_nd_Remove_Dups {
def main(args:Array[String]):Unit={

  Logger.getLogger("org").setLevel(Level.OFF)
  val ss = new SparkSession.Builder().appName("**Bigdata**").master("local[*]").getOrCreate()

  val data = Seq(("Deepak",35),("Vijay",25),("Savita",30),("Deepak",35))
  val schema = List("name","age")

  val df = ss.createDataFrame(data).toDF(schema:_*)
  df.show()  // showing the schema data

  val dup_value = df.dropDuplicates(Seq("name","age"))
  dup_value.show()   // removing the duplicate record
}
}
