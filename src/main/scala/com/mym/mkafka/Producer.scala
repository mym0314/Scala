package com.mym.mkafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object Producer extends App{
  val topic ="spark"
  val brokers = "192.168.124.53:9092"
  val props = new Properties()
  props.put("bootstrap.servers",brokers)
  props.put("client.id","ScalaProducerExample")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String ,String](props)
  val t = System.currentTimeMillis()
  val msg = " test Kafka Producer"
  val record = new ProducerRecord[String,String](topic,"key",msg)
  producer.send(record)
  producer.close()
}
