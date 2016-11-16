package demo.stream

import java.text.SimpleDateFormat

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by hdhamee on 11/15/16.
 */
object TCPSocketStreamApp {
  val dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  def main(args: Array[String]) {
    //create spark context
    val config = new SparkConf().setAppName("tcp-stream").setMaster("local[2]")
    val sc = new SparkContext(config)
    sc.setLogLevel("WARN")

    // create streaming context
    val ssc = new StreamingContext(sc,Seconds(5)) // batch interval 5 sec

    // create tcp socket stream
    val tcpDStream = ssc.socketTextStream("localhost",9999).window(Seconds(20),Seconds(10))
    tcpDStream.map(line => line)
    tcpDStream.print() // outputting function needed


    ssc.start() // start computation
    ssc.awaitTermination() // wait for completion
  }
  // start tcp server: nc -lk 9999
}
