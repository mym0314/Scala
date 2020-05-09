package com.mym.mkafka.kafkaspark

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


object KafkaToSpark extends App {

    val brokerList = "192.168.124.53:9092"
    val kafkaTopic = "spark"
    val groupId = "spark"
    val conf = new SparkConf().setAppName("KafkaToSpark").setMaster("local")
    val ssc = new StreamingContext(conf, Seconds(5))

    val kafkaPararms = Map[String, String](
      "metadata.broker.list" -> brokerList,
       "group.id" -> groupId
    )
    val topics = kafkaTopic.split(",").toSet

    val message: InputDStream[(String, String)] =
      KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaPararms, topics)

    val sensorData = message.map(x => x + "********spark++")
    sensorData.print()
    //sensorData.foreachRDD(println(_))


  ssc.start()
  ssc.awaitTermination()

}
