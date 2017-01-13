package demo.session

import org.apache.spark.sql.SparkSession

/**
  * Created by hdhamee on 11/15/16.
  */
object SparkSessionExample {

  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder
      .master("local")
      .appName("spark session example")
      .getOrCreate()

    val df = sparkSession.read.option("header","true").csv("src/main/resources/sales.csv")

    df.show()

  }
}
