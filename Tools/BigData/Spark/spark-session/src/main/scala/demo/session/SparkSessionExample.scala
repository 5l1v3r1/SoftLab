package demo.session

import org.apache.spark.sql.SparkSession

/**
  * Created by hdhamee on 11/15/16.
  */
object SparkSessionExample {

  def main(args: Array[String]) {

    //Step 1 : Create SparkSession
    val sparkSession = SparkSession.builder
      .master("local")
      .appName("spark session example")
      .getOrCreate()

    //Step 2 : Read data
    val dataFrame = sparkSession.read.text("src/main/resources/sales.csv")

    //Step3 : Show data
    dataFrame.show()
  }
}
