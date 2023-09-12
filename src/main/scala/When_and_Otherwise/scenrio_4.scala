package When_and_Otherwise

import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.{col, when, _}
import java.io.Serializable;

/*4)Given a DataFrame df with columns age and income, create a new column age_group where you categorize
age into "Young" if it's less than 30,
"Adult" if it's between 30 and 60 (inclusive), and
"Senior" if it's greater than 60.
If age is null, set age_group to "Unknown".
  Multiple Columns and Complex Conditions:*/

object scenrio_4 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

    val data = Seq((58,50000),(60,22000),(21,10000),(45,35000),(68,25000)).toDF("age","income")

    val pp = data.withColumn("age_group",
      when(col("age")<30,"Young")
     .when(col("age")>=30  && (col("age")<60),"Adult")
     .when(col("age")>=60 , "Senior")
//        .when(col("age").isNull,"Unknown")
        .otherwise("SomeOtherValue"))

      pp.show()

//val dfWithAgeGroup = data.withColumn("age_group", when($"age".isNull, "Unknown").otherwise("SomeOtherValue"))
//    dfWithAgeGroup.show()

  }
}
