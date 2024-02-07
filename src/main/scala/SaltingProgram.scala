import org.apache.spark.SparkContext

object SaltingProgram {
def main(args:Array[String]):Unit={

  val sc =new SparkContext(master ="local[*]", appName = "SparkwebUI")

  val rawdata = Seq(
    ("key1", 1),
    ("key2", 2),
    ("key3", 3),
    ("key4", 4),
    ("key1", 5),
    ("key1", 6),
    ("key1", 7),
    ("key5", 8))

  val skewRDD = sc.parallelize(rawdata)

  val saltedRDD = skewRDD.map(x => (s"${x._1}_${scala.util.Random.nextInt(10)}", x._2))

  val numpartition = 4
  val repartitionRDD = saltedRDD.repartition(numpartition)

  val result = repartitionRDD.collect()
  result.foreach(println)

  scala.io.StdIn.readLine()
  // sc.stop()
}
}
