import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

object ParellriseMethod {

    def main(args: Array[String]): Unit = {

      // Create Spark configuration
      val sc = new SparkContext("local[8]", "karthik")

      val arr = Array(1, 2, 3, 4, 5, 6, 3)

      val input = sc.parallelize(arr) // this data in array & we have to convert into rdd in parallelize()

      val rdd2 = input.mean()   // mean function used to average the array values

      rdd2.foreach(println)
      scala.io.StdIn.readLine()

    }
}
// output will be 3.4285714285714284
