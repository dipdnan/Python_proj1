import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.dsl.expressions.doubleToLiteral
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.col

object If_Else_Program3 {

def main(args:Array[String]):Unit={

  val ss = new SparkSession.Builder().appName("Welcome to SPARK").master("local[*]").getOrCreate()
  import ss.implicits._
  val df = List(("Keyboard",450),("Mouse",350),("Mouse_pad",100),("Data_Cable",50)).toDF("Product_Name","Price")

 val new_col = df.withColumn("Discounted_Price",
   when(col("Price")> 150,col("Price")*0.9)
   .otherwise("150"))

  new_col.show()

//  val categorizedDF = df.withColumn("Age_Group",
//    when(col("age") < 18, "Child")
//      .when(col("age") > 18 and col("age") <= 30, "Young")
//      .when(col("age") > 30, "adult").otherwise("No Age bar"))
}
}
