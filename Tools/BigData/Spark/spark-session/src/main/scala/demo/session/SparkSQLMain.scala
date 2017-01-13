package demo.session

import java.text.SimpleDateFormat

import org.apache.hadoop.fs.Path
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by hdhamee on 11/15/16.
 */
object SparkSQLMain {
  val dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  def main(args: Array[String]) {
    /*// table schema
    val schemaString = "name age degree"
    val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName,StringType,true)))

    // spark context
    val appName = "Spark SQL Interactive data processing"
    val conf = new SparkConf().setAppName(appName).setMaster("local[2]")
    val sc = new SparkContext(conf)

    // sql context
    val sqlContext = new SQLContext(sc)

    //data source
    val rawRdd = sc.textFile("data/data.txt").map(line => line.split(" ",-1)).map(a => Row(a(0),a(1),a(2)))

    // sql operation
    val df = sqlContext.createDataFrame(rawRdd,schema)
    val a =  df.select("name","age","degree").filter(df("age") > 30).show()*/
    println(new Path("Member/Hikmat Singh Dhamee.txt").toUri)
    // stop driver program
    //sc.stop()

  }
}
