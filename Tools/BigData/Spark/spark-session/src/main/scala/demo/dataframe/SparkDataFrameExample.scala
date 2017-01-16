package demo.dataframe

import org.apache.spark.sql.SparkSession

/**
  * Created by hdhamee on 1/13/17.
  */
object SparkDataFrameExample {
  def main(args: Array[String]) {

    //Step 1 : Create SparkSession
    val sparkSession = SparkSession.builder
      .master("local")
      .appName("spark session example")
      .getOrCreate()

    //Step 2 : Read data and convert to Dataset
    import sparkSession.implicits._
    val data = sparkSession.read.text("src/main/resources/data.txt").as[String]
    val df = sparkSession.read.option("header","true").csv("src/main/resources/sales.csv")

    df.show()

  }
}
