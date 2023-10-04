import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col}

object demo_ShufflesortMergeJoin {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import spark.implicits._

  val data1 = spark.createDataFrame(Seq(
     (1,"Jon"),
     (2,"Mike"),
     (3,"Sam")
  )).toDF("id","name")


  val data2 = spark.createDataFrame(Seq(
    (1,50000),
    (2,65000),
    (3,75000)
  )).toDF("id","salary")

//  val data1_df = spark.sparkContext.broadcast(data1)

  // performing the inner join with 2nd dataframe to 1st dataframe
//  val reslt_df = data1.join(data2,Seq(col("id")),"inner")
//
//  reslt_df.show()

  spark.stop()
}
}
