package Company_Scenrios
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, explode}

object Explode_Subjects_of_Students {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder()
    .appName("webUI")
    .master("local[*]")
    .getOrCreate()

  val inputData = Seq(
    ("Nitin",Seq("Science","Math","Geo")),
    ("Deepak",Seq("Phy","Chem","Bio"))
  )

   val schema = List("Student","Subjects")
  val df = spark.createDataFrame(inputData).toDF(schema:_*)

  val explodedDF = df.select(col("Student"), explode(col("Subjects")).alias("Subject"))

  explodedDF.show()
}
}
