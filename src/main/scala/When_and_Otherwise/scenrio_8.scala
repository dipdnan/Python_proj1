package When_and_Otherwise

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, concat, lit, when};

object scenrio_8 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

      val data = List(
        ("Deepak","Nanaware"),
        ("Vijay","Nanaware"),
        ("Karthik","Kondhapak"),
        (null,"abc"))
      val df = data.toDF("First_Name", "Last_Name")

    val dfrdd = df.select("First_Name","Last_Name")
        .withColumn("Full_Name",
          when(col("First_Name").isNull || col("Last_Name").isNull,"Unknown")
            .otherwise(concat(col("First_Name"),lit(" "),col("Last_Name")))
        )
    dfrdd.show()


  }
}
