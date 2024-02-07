package Company_Scenrios
import org.apache.spark.sql.SparkSession

object Display_Missing_Values {
def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("webUI").master("local[*]").getOrCreate()
  import ss.implicits._

  val data = Seq(1,2,3,5,7,9,13)
  val df_numbers = ss.sparkContext.parallelize(data).toDF("numbers")

  val df_range = ss.range(1,15).toDF("numbers")

  val missing_no = df_range.join(df_numbers,Seq("numbers"),"left_anti")

  val result = missing_no.select("numbers").orderBy("numbers").show()

} }
