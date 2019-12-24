package com.mym.sparksql

import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

object AccessConvertUtil {
  val struct = StructType(
    Array(
      StructField("url",StringType),
      StructField("classType",StringType),
      StructField("classId",LongType),
      StructField("traffic",LongType),
      StructField("ip",StringType),
      StructField("city",StringType),
      StructField("time",StringType),
      StructField("day",StringType)
    )
  )
  def parseLog(log:String): Row ={
    try{
      val splits =log.split("\t")
      val url = splits(1)
      val traffic = splits(2).toLong
      val ip = splits(3)
      val domain = "http://www.imooc.com/"
      val className = url.substring(url.indexOf(domain)+domain.length)
      val classTypeId = className.split("/")

      var classType = ""
      var classId = 0l

      if (classTypeId.length > 1) {
        classType = classTypeId(0)
        if (classId.isInstanceOf[Long]){
          classId = classTypeId(1).toLong
        }else{
          classId=0l
        }

      }

      val city = IpUtils.getCity(ip)
      val time = splits(0)
      val day = time.substring(0, 10).replaceAll("-", "")

      // row要和struct对应上
      Row(url, classType, classId, traffic, ip, city, time, day)

    }catch{
      case e:Exception=>Row("","",0l,0l,"","","","")
    }
  }


}
