import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.col

object If_Else_Rename_Column {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Spark").master("local[*]").getOrCreate()
    import ss.implicits._

    val abc = Seq(("A",25),("B",20),("C",30),("D",32)).toDF("Name","Age")

    val ap = abc.withColumn("New_column",
      when(col("Age")<=25 ,"Young").otherwise("old")as "category")

    ap.show()
  }
}
