import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, dense_rank, desc, rank}
import org.apache.spark.sql.types
object demo_BroadcastJoin {
def main(args:Array[String]):Unit={

  val spark = new SparkSession.Builder().appName("bigdata").master("local[*]").getOrCreate()
  import spark.implicits._

  val data1 = List((1,"A",100),(2,"AA",110),(5,"B",200),
    (5,"B",200),(5,"B",200),(5,"B",200),(5,"B",200),
    (5,"B",200),(10,"AB",120)).toDF("ID","NAME","SALARY")

  val data2 = List((1,"A",100),(2,"AA",110),(3,"B",200),(5,"AB",120)).
    toDF("ID", "NAME_1", "SALARY_1")

  val data1_df = spark.sparkContext.broadcast(data1)

  // performing the inner join with 2nd dataframe to 1st dataframe
  val reslt_df = data2.join(data1_df.value,"ID").orderBy("ID")

  reslt_df.show()

  scala.io.StdIn.readLine()

}
}
