import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

object using_sortby_example {

  def main(args:Array[String]):Unit={

  val sc = new SparkContext("local[8]","Deepak")

    val rdd = sc.parallelize(Array(1,1,2,3,4,5,5,4,3,3))

    val unq = rdd.distinct().collect.foreach(println)
    scala.io.StdIn.readLine()

  }
}
