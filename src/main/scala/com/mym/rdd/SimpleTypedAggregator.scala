package com.mym.rdd

import org.apache.spark.sql.{Encoder, Encoders, SparkSession}
import org.apache.spark.sql.expressions.Aggregator

object SimpleTypedAggregator {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("common typed aggregator implementations")
      .getOrCreate
    import spark.implicits._
    val  ds = spark.range(20).select(('id % 3).as("key"), 'id).as[(Long, Long)]
    println("input data:")
    ds.show()

    println("running typed sum:")
    ds.groupByKey(_._1).agg(new TypedSum[(Long,Long)](_._2).toColumn).show()

  }
  class TypedSum[IN](val f: IN => Long) extends Aggregator[IN, Long, Long] {
    override def zero: Long = 0L
    override def reduce(b: Long, a: IN): Long = b + f(a)
    override def merge(b1: Long, b2: Long): Long = b1 + b2
    override def finish(reduction: Long): Long = reduction

    override def bufferEncoder: Encoder[Long] = Encoders.scalaLong
    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }

}
