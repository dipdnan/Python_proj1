import org.apache.spark.SparkContext
object WORD_COUNT_SCALA {
def main(args:Array[String]):Unit={

  val sc = new SparkContext("local[8]","deepaknanaware")

//  val input = sc.textFile("C:/Users/Ganesh/Desktop/SRC_Files/aa.txt")

  val inputData = "I am Deepak. I am Data Engineer"
  val words = sc.parallelize(inputData.split("\\s+"))

//  val words = input.flatMap(x=>x.split(" "))
//  words.collect.foreach(println)


  val wordsmap = words.map(x=>(x,1)).reduceByKey(_ + _)
  wordsmap.collect.foreach(println)

//  val wordcnt = wordsmap.reduceByKey((x,y)=> x + y)
//  wordcnt.collect.foreach(println)

  scala.io.StdIn.readLine()


}
}
