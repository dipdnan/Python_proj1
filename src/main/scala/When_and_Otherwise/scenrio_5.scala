package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, when};

object scenrio_5 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = Seq((42,65),(35,60),(29,63),(32,45),(25,50),(41,62))
      val df = data.toDF("temp","humidity")

    val dfrdd = df.withColumn("weather_type",
      when(col("temp")>=30 && col("humidity")>=60 , "Hot and Humid")
     .when(col("temp")<30  && col("humidity")>=60 ,"Warm and Humid")
     .when(col("temp")>=30 && col("humidity")<60, "Hot and Dry")
        .when(col("temp")<30 && col("humidity")<60, "Moderate")
//        .when(col("age").isNull,"Unknown")
        .otherwise("nothing"))

    dfrdd.show()



  }
}
