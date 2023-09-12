package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, when};

object scenrio_9 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = Seq(
        (50000, 10000),
        (75000, 15000),
        (40000, 0),
        (30000, 15000),
        (25000, 10000),
        (8000, 8000),
        (5000, 10000))
      val df = data.toDF("sales", "expenses")

    val aa = df.withColumn("profit_status",

         when(col("sales") > col("expenses") &&
         col("sales")>0 && col("expenses") >0,"profittable")

         .when(col("sales") > col("expenses") ||
         col("sales")==col("expenses"),"Break-even")

         .when(col("sales")>0 && col("expenses")>0 && col("expenses")> col("sales"),"Loss-making")
         .when(col("sales")> 0 && col("expenses")==0,"No Expenses")
         .otherwise("Unknown"))

    aa.show()



  }
}
