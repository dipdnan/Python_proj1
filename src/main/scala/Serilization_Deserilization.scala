import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions
object Serilization_Deserilization {
  def main(args:Array[String]):Unit={

   val ss = SparkSession.builder()
      .appName("data")
      .master("local[*]")
      .getOrCreate()

    import ss.implicits._

    val data = Seq("deepak", "sandip","munna").toDF("name")

    data.write
      .format("avro")
      .save("C:/Users/Ganesh/Downloads/src_files/serialization1.avro")

  }
}
