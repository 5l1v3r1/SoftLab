package demo.dataframe

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

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

    //Step 2 : Read data and convert to dataframe
    val df = sparkSession.read
      .option("header","false")
      .option("delimiter",";")
      .option("charset", "UTF8")
      .schema(itemSchema)
      .csv("src/main/resources/sales.csv")

    //Step 3 : Split and group by word
    import org.apache.spark.sql.functions._
    val words = df.groupBy("ITEM_NAME").agg(sum("ITEM_PRICE"),max("ITEM_QTY"))

    //Step 4: Print results
    words.show()

  }

  val itemSchema = StructType(
    Array(
      StructField("ITEM_NAME", StringType),
      StructField("ITEM_QTY", LongType),
      StructField("ITEM_PRICE", DoubleType))
  )
}
