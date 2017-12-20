package com.mym.mkafka

import java.util.{Collections, Properties}

import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._




object Consumer extends  App{

  val topic = "producer"
  val brokers = "19.224.1.119:9092"
  val props = new Properties()
  props.put("bootstrap.servers",brokers)
  props.put("client.id","ScalaConsumerExample")
  props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id","something")

  val consumer = new KafkaConsumer[String,String](props)
  consumer.subscribe(Collections.singleton(topic))
  val records = consumer.poll(1000)
  for(record <- records.asScala){
    println(record)
  }
  consumer.close()

}
