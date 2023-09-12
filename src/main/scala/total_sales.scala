import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.storage.StorageLevel

object total_sales {

  def main(args:Array[String]):Unit={

    val sc = new SparkContext("local[8]","Deepak")

    val rdd = sc.parallelize(Array(1,2,3,4,5,6,7,8,9,10))

    val sum = rdd.reduce(_ + _)
            val count = rdd.count()

    println("The total sales are : " +sum)
    scala.io.StdIn.readLine()

  }
}
