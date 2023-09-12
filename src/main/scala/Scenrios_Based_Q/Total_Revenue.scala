package Scenrios_Based_Q

import org.apache.spark.SparkContext

object Total_Revenue {

  def main(args:Array[String]):Unit={

    val sc = new SparkContext("local[8]","Deepak")

    val sc1 = sc.parallelize(Array(10,20,30,40,50))

    val sum = sc1.reduce(_+_)

    val cnt = sc1.count()
    println(s"Total sum is : $sum")
  }
}
