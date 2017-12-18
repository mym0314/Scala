package com.mym.rdd

import org.apache.spark.sql.SparkSession;

object BroadcastTest {
  def main(args: Array[String]): Unit = {
    val blockSize = if(args.length>2)args(2) else "4096"

    val spark = SparkSession
      .builder()
      .master("local")
      .appName("Broadcast Test")
      .config("spark.broadcast.blocksize",blockSize)
      .getOrCreate()
    val sc = spark.sparkContext
    val slices = if(args.length>0)args(0).toInt else 2
    val num = if(args.length>1)args(1).toInt else 2048

    val arr1 = (0 until num).toArray

    for(i<- 0 until 3){
      println("Iteration"+i)
      println("============")
      val startTime = System.nanoTime()
      val barr = sc.broadcast(arr1)
      val observedSize = sc.parallelize(1 to 10,slices).map(_=>barr.value.length/2)
      observedSize.collect().foreach(println(_))
      println("Iteration %d took %.0f milliseconds".format(i,(System.nanoTime()-startTime)/1E6))
    }
    spark.stop()
    //    val blockSize = if (args.length > 2) args(2) else "4096"
    //
    //    val spark = SparkSession
    //      .builder()
    //      .master("local")
    //      .appName("BroadcastTest")
    //      .config("spark.broadcast.blocksize", blockSize)
    //      .getOrCreate()
    //
    //    val sc = spark.sparkContext
    //    val slices = if (args.length > 0) args(0).toInt else 2
    //    val num = if (args.length > 1) args(1).toInt else 9999
    //
    //    val arr1 = (0 until num).toArray
    //    for (i <- 0 until 3){
    //      println("Iteration"+i)
    //      println(("==========="))
    //      val startTime = System.nanoTime()
    //      val barr = sc.broadcast(arr1)
    //      val observedSize = sc.parallelize(1 to 10 ,slices).map(_=>barr.value.length)
    //      observedSize.collect().foreach(i=>println(i+"**"))
    //      println("Iteration %d took %.0f milliseconds".format(i,(System.nanoTime()-startTime)/1E6))
    //    }
    //    spark.stop()

  }

}
