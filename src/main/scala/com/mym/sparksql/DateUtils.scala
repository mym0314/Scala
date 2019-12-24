package com.mym.sparksql

import java.util.{Date, Locale}

import org.apache.commons.lang3.time.FastDateFormat

object DateUtils {
  val SOURCE_TIME_FORMAT = FastDateFormat.getInstance("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)

  val TARGET_TIME_FORMAT= FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")

  def parse(time:String): String ={
    TARGET_TIME_FORMAT.format(new Date(getTime(time)))
  }
  def getTime(time:String): Long ={
    try {
      SOURCE_TIME_FORMAT.parse(time.substring(time.indexOf("[") + 1, time.lastIndexOf("]"))).getTime
    }catch{
      case e:Exception =>{
        0l
      }
      }
  }

  def main(args: Array[String]): Unit = {
    println(parse("[10/Nov/2016:00:01:02 +0800]"))
  }

}
