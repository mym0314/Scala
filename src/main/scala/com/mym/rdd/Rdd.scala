package com.mym.rdd

import org.apache.spark.{SparkConf, SparkContext}

object Rdd {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RDD").setMaster("local")
    val sc = new SparkContext(conf)
    //Map
    val array = Array(1,2,3,4,5,6,7,8)
    val arrayMap = sc.parallelize(array)
    val rddMap = array.map(x=>x*2)

    rddMap.foreach(println(_))

    //sortBy
    val data = List(3,1,90,3,5,12)

    val rdd = sc.parallelize(data)

    val result = rdd.sortBy(x => x, false, 1)

    result.foreach(println(_))

    //sourByKey
    val a =sc.parallelize(List("wyp", "iteblog", "com","397090770", "test"),1)

    val b =sc.parallelize(List(3,1,9,12,4))

    val c = b.zip(a)

    val aa =c.sortByKey().collect

    aa.foreach(println(_))

    //flatMap
    val mFlatMap = sc.parallelize(1 to 10)
    val mFlatResult = mFlatMap.flatMap(x => 1 to x)

    mFlatResult.foreach(print(_))

    sc.stop()
  }

}
