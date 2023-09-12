import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.{col, when, _}

object Starts_Endswith_Prog {
 def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Welcome to Spark").master("local[*]").getOrCreate()
    import ss.implicits._
    val aa = List(("Abae",10),("Batish",21),("Aatu",32),("Anna",54),("Bachin",22),("sachin",30)).toDF("name","age")

//    val ab = aa.filter(col("name").startsWith("A")).show()
//    val ac = aa.filter(col("name").endsWith("e")).show()

    val ba = aa.select(
      col("name"),
      col("age"),
      when(col("age")>30 && col("name").startsWith("A"),"Senior")
        .when(col("age")>20 && col("name").endsWith("in"),"Midsenior")
        .otherwise("Junior") as ("position"))

        ba.show()
       val pq = aa.filter(col("name").contains("in")).show()
//      val ppp =aa.filter(col("age")> 30 ).show()

//       val nn =aa.filter(col("age").isNotNull).show()







  }
}
