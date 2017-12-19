package com.mym.rdd

import org.apache.spark.sql.SparkSession

object HdfsTest {

  def main(args: Array[String]): Unit = {
    if(args.length<1){
      System.err.println("Usage: HdfsTest <file>")
      System.exit(1)
    }

    val spark = SparkSession
      .builder()
      .appName("HdfsTest")
      .getOrCreate()

    val file = spark.read.text(args(0)).rdd

    val mapped = file.map(s=>s.length).cache()

    for(item <- 1 to 10 ){
      val start =System.currentTimeMillis()
      for(x<-mapped){x+2}
      val end = System.currentTimeMillis()
      println("Iteration " + item + " took " + (end-start) + " ms")
    }
    spark.stop()
  }

}
