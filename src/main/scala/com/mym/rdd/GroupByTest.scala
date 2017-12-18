package com.mym.rdd

import org.apache.spark.sql.SparkSession

import scala.util.Random

object GroupByTest {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("GroupBy")
      .getOrCreate()

    val numMapper = if (args.length > 0) args(0).toInt else 2
    val numKVPairs = if (args.length > 1) args(1).toInt else 10000
    val valSize = if (args.length > 2) args(2).toInt else 10000
    val numReducers = if (args.length > 3) args(3).toInt else numMapper

    val pairs1 = spark.sparkContext.parallelize(0 until numMapper, numMapper)
      .flatMap { p =>
        val ranGen = new Random
        val arr1 = new Array[(Int, Array[Byte])](numKVPairs)
        for (i <- 0 until numKVPairs) {
          val byteArr = new Array[Byte](valSize)
          ranGen.nextBytes(byteArr)
          arr1(i) = (ranGen.nextInt(Int.MaxValue), byteArr)
        }
        arr1
      }.cache
    pairs1.count()
    println(pairs1.groupByKey(numReducers).count())

    spark.stop()
  }

}
