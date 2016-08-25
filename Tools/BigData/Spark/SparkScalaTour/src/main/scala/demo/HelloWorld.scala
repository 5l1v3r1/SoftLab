package demo

import org.apache.spark.{SparkConf, SparkContext}

object HelloWorld {

  def main(args: Array[String]) :Unit = {

    // Initializing Spark: The Driver program
    val conf = new SparkConf().setAppName("SparkOperations").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // Two kinds of RDD: Parallelizing an existing collection in your driver program,
    // or referencing a dataset in an external storage system, such as a shared filesystem, HDFS.

    //[1] In-program Collection
    val data = Array(1, 2, 3, 4, 5)
    val distData = sc.parallelize(data,2)
    val sum = distData
      .reduce((a,b) => a + b)
    val s = sc.parallelize(Seq(sum),1)

    s.saveAsTextFile("output/output1.txt")

    //[2] External Collection
    val distFile = sc.textFile("data/input.txt",2)// no need to specify minPartition in case of HDFS
    val counts = distFile
        .flatMap(line => line.split(" "))
        .map(word => (word, 1))
        .reduceByKey(_ + _)
    counts.saveAsTextFile("output/output2.txt")

    //RDD Operations:Transformations and Actions


  }
}
