import org.apache.spark.sql.{ColumnName, Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions.{col, when}



object Repartition_Coalesce_demo {
def main(args:Array[String]):Unit={

  Logger.getLogger("org").setLevel(Level.OFF)
  val ss = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import ss.implicits._

  val schema11 = "Id Int,Name String,Salary Int,dept_id Int"
  val df = ss.read
    .format("csv")
    .option("header",true)
    .option("InferSchema",true)
    .schema(schema11)
    .option("path","C:/Users/Ganesh/Downloads/src_files/src_inp_partition_coalesce.csv")
    .load()

//    ---------------------------------------------------------------------
  //    println("***********Before loading data into dataframe**********")
//    df.show()

//    println(s"***********Repartition data into 5 partition ***************")
//    val repartition = df.repartition(5)
//    repartition.show()
// --------------------------------------------------------------------------------------------------
    println(s" ************* Partition by Id column *************** ")
    val repartition1 = df.repartition(5,col("Id")).orderBy("Id")
    repartition1.show()

    println(s" ************* coalesce by 2 *************** ")
   val aa = df.coalesce(2)
   aa.show()
//  ----------------------------------------------------------------------------------------------------
//     println("First sample dataframe ===========")
//     val first_sampledf = df.sample(0.10)
//     first_sampledf.show()
//
//     println("Second sample dataframe ===========")
//     val second_sample = df.sample(0.01)
//     second_sample.show()
//
//     val collected_data = second_sample.collect()
//     println("*********** Collected Data from Second Sample **********")
//     collected_data.foreach(println)
//
//     println("first sample dataframe below--------------------")
//     first_sampledf.collect.foreach(println)
//  -------------------------------------------------------------------------------------

}
}
