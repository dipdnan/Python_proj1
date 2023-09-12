import org.apache.spark.sql.SparkSession
object sample1 {

  def main(args: Array[String]): Unit = {

    // Create a SparkSession
    val spark = SparkSession.builder()
      .appName("Spark SQL basic example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    // Load a CSV file as a DataFrame
    val df = spark.read.csv("C:/Users/Ganesh/Desktop/annual-enterprise-survey-2021-financial-year-provisional-csv")

    // Show the first few rows of the DataFrame
    df.show()

    // Your code for data processing using Spark can go here
    // Stop the SparkSession when you're done
    spark.stop()
  }
}