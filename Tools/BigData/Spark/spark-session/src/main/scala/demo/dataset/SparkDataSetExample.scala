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
    val salesDS = sparkSession.read
      .option("header","false")
      .option("delimiter",";")
      .option("charset", "UTF8")
      .schema(itemSchema)
      .csv("src/main/resources/sales.csv")
      .as[ItemRow]

    val productsDS = sparkSession.read
      .option("header","false")
      .option("delimiter",";")
      .option("charset", "UTF8")
      .schema(itemSchema)
      .csv("src/main/resources/products.csv")
      .as[ItemRow]
      .cache()

    //Step 3 : Filter and join by PK
    val filteredDs = salesDS.filter(_.ITEM_QTY > 1)

    val  joinedDs = productsDS.join(salesDS,salesDS("ITEM_NAME")===productsDS("ITEM_NAME"))

    import org.apache.spark.sql.functions._
    val aggsDs = joinedDs
      .groupBy(salesDS("ITEM_NAME"))
      .agg(sum(productsDS("ITEM_PRICE")),max(salesDS("ITEM_QTY")))

    // UDFs
    // Define a regular Scala function
    def toUpper = (x:String,y:String) => x+y

    // Define a UDF that wraps the upper Scala function defined above
    // You could also define the function in place, i.e. inside udf
    // but separating Scala functions from Spark SQL's UDFs allows for easier testing
    import org.apache.spark.sql.functions.udf
    def toUpperUDF = udf(toUpper)

    val upperDs = aggsDs.withColumn("ITEM_NAME",toUpperUDF(aggsDs("ITEM_NAME"),lit("_test")))

    //Step 4: Print results
    upperDs.show()

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
