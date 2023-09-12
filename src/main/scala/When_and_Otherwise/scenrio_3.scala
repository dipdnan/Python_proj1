package When_and_Otherwise
import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.{col, when, _}

object scenrio_3 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata Developer").master("local[*]").getOrCreate()
    import ss.implicits._

    val aa = List((30000),(25000),(55000),(68000),(78000),(92000)).toDF("Income")

    val ab = aa.withColumn("Categorise",
            when(col("Income")<= 30000 , "Low")
            .when(col("Income")>= 30000 && (col("Income")<= 70000),"Medium")
            .when(col("Income")> 70000 , "High"))

    ab.show()

  }
}
