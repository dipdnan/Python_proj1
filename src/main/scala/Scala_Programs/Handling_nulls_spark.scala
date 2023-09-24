package Scala_Programs

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, when}

object Handling_nulls_spark {
def main(args:Array[String]):Unit={

  Logger.getLogger("org").setLevel(Level.OFF)
  val ss = new SparkSession.Builder().appName("**Bigdata**").master("local[*]").getOrCreate()

  val dfschema1 = "name String, age Int";
  val data = ss.read
    .format("csv")
    .option("header",true)
//    .option("nullValue",null)
    .schema(dfschema1)
    .option("path","C:/Users/Ganesh/Downloads/src_files/src_null_related_file.csv")
    .load()

   data.show()
   println(s"Before filter null data is to be==  ${data.count()}")

   val filterdf = data.filter(col("age").isNotNull)
   println(s"after doing some operation ====    ${filterdf.count()}")
  filterdf.show()

// replacing the null values to 0 for age column.
   val readcsv = data.withColumn("AGE",
     when(col("age").isNull,"0").otherwise(col("age")))

  readcsv.show()



}
}
