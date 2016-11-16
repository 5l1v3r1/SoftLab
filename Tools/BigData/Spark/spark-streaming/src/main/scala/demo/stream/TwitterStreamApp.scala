package demo.stream

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by hdhamee on 11/15/16.
 */
object TwitterStreamApp {
  val dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  /**
   * Using a subset of a Twitter stream was the perfect choice to use in this demonstration,
   * since it had everything we needed: an endless and continuous data source that was ready to be explored.
   *
   * @param args
   */
  def main(args: Array[String]) {
    //Twitter credentials
    val consumerKey = ""
    val consumerSecret = ""
    val accessToken = ""
    val accessTokenSecret = ""

    //create spark context
    val config = new SparkConf().setAppName("twitter-stream-sentiment").setMaster("local[2]")
    val sc = new SparkContext(config)
    sc.setLogLevel("WARN")

    // create streaming context
    val ssc = new StreamingContext(sc, Seconds(5))

    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)

    // create twitter stream
    val stream = TwitterUtils.createStream(ssc, None)

    //What is Trending Right Now on Twitter? ----- Processing operation here
    /**
     * It is easy to find out what is trending on Twitter at any given moment;
     * it is just a matter of counting the appearances of each tag on the stream
     */
    val tags = stream
      .flatMap({ status =>
        status.getHashtagEntities.map(_.getText)})

    tags
      .countByValue()
      .foreachRDD { rdd =>
        val now = dateFormat.format(new Date())
        rdd
          .sortBy(_._2)
          .map(x => (x, now))
          .saveAsTextFile(s"twitter/$now")
      }

    ssc.start() // start computation
    ssc.awaitTermination() // wait for completion
  }

  // We could build some interesting dashboards using this saved information in order to track the most trending hashtags.

}
