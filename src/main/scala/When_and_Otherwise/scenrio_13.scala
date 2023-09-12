package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, when};

object scenrio_13 {

  def main(args:Array[String]):Unit={

        /*  13)Given a DataFrame df with a column email, create two new columns: username and domain.
        Extract the username (characters before '@') and domain (characters after '@') from the email
        column. If the email is null or does not contain '@', set both username and domain to "Unknown."
        Nested Conditions and Ranking: */

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
