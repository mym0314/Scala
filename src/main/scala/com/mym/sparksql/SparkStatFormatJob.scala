package com.mym.sparksql


import org.apache.spark.sql.SparkSession

object SparkStatFormatJob {

  def main(args: Array[String]): Unit = {
    val spark =SparkSession
      .builder()
      .appName("SparkStatFormatJob")
      .master("local")
      .getOrCreate()

    val access = spark.sparkContext.textFile("C:\\Users\\11200\\Desktop\\access.log")
    access.map(line=>{
      val splits=line.split(" ")
      val ip = splits(0)
      val time = splits(3)+" "+splits(4)
      val url = splits(11).replaceAll("\"","")
      val traffic = splits(9)
      DateUtils.parse(time)+"\t"+url+"\t"+traffic+"\t"+ip
    }).saveAsTextFile("C:\\Users\\11200\\Desktop\\access_clean.log")
    spark.stop()
  }

}
