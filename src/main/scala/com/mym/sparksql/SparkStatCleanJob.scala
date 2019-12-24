package com.mym.sparksql

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkStatCleanJob {


    def main(args: Array[String]): Unit = {

      val spark = SparkSession.builder().appName("SparkStatCleanJob").master("local[2]").getOrCreate()

      val accessRDD = spark.sparkContext.textFile("C:\\Users\\11200\\Desktop\\access_format.log")

      val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)),
        AccessConvertUtil.struct)

      accessDF.printSchema()
      accessDF.show()

      // coalesce:设置输出文件的个数，默认3个
      accessDF.coalesce(1).write.format("json").mode(SaveMode.Overwrite)
        .partitionBy("day").save("C:\\Users\\11200\\Desktop\\access_clean01.log")
    }


}
