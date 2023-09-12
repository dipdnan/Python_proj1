package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, when};

object scenrio_12 {

  def main(args:Array[String]):Unit={

        /*  12)Given a DataFrame df with columns timestamp, temperature, and humidity, create a new column weather_condition based on the following conditions:
        If temperature is less than 0 and humidity is greater than 80, set weather_condition to "Extreme Cold and Humid".
        If temperature is between 0 and 10 (inclusive) and humidity is between 50 and 80 (inclusive), set weather_condition to "Cold and Moderate Humidity".
        If temperature is greater than 10 and humidity is less than 50, set weather_condition to "Warm and Dry".
        For all other cases, set weather_condition to "Other".  */
    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = List(
        (1, 10,50),
        (2, -1,85),
        (3,10,65),
        (0,5,59),
        (5,5,60),
        (6,25,38),
        (0,22,28))
      val df = data.toDF("timestamp", "temperature", "humidity")
    val dfrdd = df.withColumn("weather_condition" ,

           when(col("temperature") < 0 && col("humidity") > 80,"Extreme Cold and Humid")

           .when(col("temperature").between(0,10) &&
           col("humidity").between(50,80),"Cold and Moderate Humidity")

             .when(col("temperature") > 10 && col("humidity") < 50 ,"Warm and Dry")
            .otherwise("OTHER"))

//    val aa = df.select(col("temperature").between(0,10) &&
//      col("humidity").between(50,80))alias("Cold and Moderate Humidity")


//        .groupby("product_id")
//        .agg(sum("revenue").alias("Total_Revenue"))
//        .orderBy(desc("Total_Revenue")))

    dfrdd.show()





  }
}
