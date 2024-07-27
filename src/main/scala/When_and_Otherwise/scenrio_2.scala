package When_and_Otherwise

import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions.{col, when, _}

object scenrio_2 {

  def main(args:Array[String]):Unit={

    val ss = new SparkSession.Builder().appName("Bigdata_Developer").master("local[*]").getOrCreate()
    import ss.implicits._

    val marks = Seq(
      (95,"Maths"),
      (89,"Geography"),
      (70,"History"),
      (65,"Polity"),
      (50,"Physices"),
      (90,"Chemistry")
    ).toDF("Score","Subjects")

    val abc = marks.withColumn("Grade",
      when(col("Score")>=90 ,"A")
        .when(col("Score")> 70 && (col("Score")<=89),"B")
        .when(col("Score")<= 70 , "C"))

        abc.show()
    scala.io.StdIn.readLine()
  }
}
/* Output :
+-----+---------+-----+
|Score| Subjects|Grade|
+-----+---------+-----+
|   95|    Maths|    A|
|   89|Geography|    B|
|   70|  History|    C|
|   65|   Polity|    C|
|   50| Physices|    C|
|   90|Chemistry|    A|
+-----+---------+-----+
 */