package demo

import org.apache.spark.SparkContext
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class Test extends FunSuite with BeforeAndAfterEach {
  var sparkSession : SparkSession = _
  var sc :SparkContext= _

  override def beforeEach(){
    sparkSession = SparkSession.builder().appName("udf testings") .master("local") .config("", "") .getOrCreate()
    sc = sparkSession.sparkContext
  }

  override def afterEach(){
    sparkSession.stop()
  }

  test("test total"){
    val sqlContext = sparkSession.sqlContext
    val dataRdd = sc.textFile("testData.csv")
    val schemaString = "memberId;paidAmt;allowedAmt"
    val schema = StructType(schemaString.split(";").map(fieldName ⇒ StructField(fieldName, StringType, true)))
    val rowRdd = dataRdd.map
    {
      line => line.split(";", -1)}
      .map
      {
        array => Row.fromSeq(array.toSeq)
      }
    val dataFrame = sqlContext.createDataFrame(rowRdd,schema)
    val testUdaf:TestUdaf = new TestUdaf(schema)

    val resultDataFrame = dataFrame.groupBy("memberId").agg(testUdaf(dataFrame.columns.map(dataFrame(_)):_*).as("totalAmountPair"))

    resultDataFrame.show(false)
    dataFrame.show()
  }
}