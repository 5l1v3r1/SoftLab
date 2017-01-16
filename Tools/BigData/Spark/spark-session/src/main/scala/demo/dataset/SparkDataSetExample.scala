package demo.dataset

import org.apache.spark.sql.SparkSession

/**
  * Created by hdhamee on 1/13/17.
  */
object SparkDataSetExample {

  def main(args: Array[String]) {

    //Step 1 : Create SparkSession
    val sparkSession = SparkSession.builder
      .master("local")
      .appName("spark session example")
      .getOrCreate()

    //Step 2 : Read data and convert to Dataset
    import sparkSession.implicits._
    val ds = sparkSession.read.option("header","true").csv("src/main/resources/sales.csv").as[String]

    //Step 3 : Split and group by word
    val words = ds.flatMap(value => value.split(";"))
    val groupedWords = words.groupByKey(_.toLowerCase)

    //Step 4 : Count
    val counts = groupedWords.count()

    //Step 5 : Print results
    counts.show()

  }
}
