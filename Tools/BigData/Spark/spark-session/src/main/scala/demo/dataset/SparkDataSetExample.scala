package demo.dataset

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

/**
  * For one thing, many operations on DataFrames involve passing in a String.
  * Either as column name or as query. This is prone to error. For example df.select(“colour”)
  * would pass at compile time and would only blow a likely long running job at run time.
  *
  * A DataFrame is basically a RDD[Row] where a Row is just an Array[Any].DF solves this.

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
    val ds = sparkSession.read
      .option("header","false")
      .option("delimiter",";")
      .option("charset", "UTF8")
      .schema(itemSchema)
      .csv("src/main/resources/sales.csv").as[ItemRow]

    //Step 3 : Split and group by word
    import org.apache.spark.sql.expressions.scalalang._

    val filteredDs = ds.filter(_.ITEM_QTY > 1)

    val aggsDs = filteredDs
      .groupByKey(Row => Row.ITEM_NAME)
      .agg(typed.sum[ItemRow](_.ITEM_PRICE),typed.sum[ItemRow](_.ITEM_QTY))

    //Step 4: Print results
    aggsDs.show()

  }

  final case class ItemRow(
                            ITEM_NAME : String,
                            ITEM_QTY : Long,
                            ITEM_PRICE : Double
                          )

  val itemSchema = StructType(
    Array(
      StructField("ITEM_NAME", StringType),
      StructField("ITEM_QTY", LongType),
      StructField("ITEM_PRICE", DoubleType))
  )

}
