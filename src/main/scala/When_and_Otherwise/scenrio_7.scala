package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, desc, sum, when};

object scenrio_7 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = Seq(
        (-1, 10, 0),
        (null, 20, 200),
        (3, 30, 300),
        (4, 10, null),
        (5, 30, 200),
        (6, 20, 250),
        (7, 10, 500))
      val df = data.toDF("sales", "expenses", "profit")

    val dfrdd = df.withColumn("profit_status",
      when(col("profit")>0 ,"Profitable")
        .when(col("profit").isNull,"Break-even")
        .when(col("profit")<0 && (col("sales")>0),"Loss-making")
        .when(col("profit")<0 && col("sales").isNull,"No-Sales"))

    dfrdd.show()


  }
}
