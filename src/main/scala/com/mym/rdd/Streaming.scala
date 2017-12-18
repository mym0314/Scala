package com.mym.rdd

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Streaming {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("Streaming");
    val ssc = new StreamingContext(conf,Seconds(1));

    val lines = ssc.socketTextStream("localhost",9999);
    val words = lines.flatMap(_.split(""));

    val pairs = words.map(word=>(word,1))
    val count = pairs.reduceByKey(_+_);

    count.print()


    ssc.start()
    ssc.awaitTermination()


  }

}
