package com.mym.mkafka

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._




object Consumer extends  App{

  val topic = "spark"
  val brokers = "192.168.124.53:9092"
  val props = new Properties()
  props.put("bootstrap.servers",brokers)
  //props.put("client.id","ScalaConsumerExample")
  props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id","spark")

  val consumer = new KafkaConsumer[String,String](props)
  consumer.subscribe(Collections.singleton(topic))
  val records = consumer.poll(5000)
  for(record <- records.asScala){
    println(record)
  }
  consumer.close()

}
