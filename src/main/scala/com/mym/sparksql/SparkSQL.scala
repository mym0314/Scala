package com.mym.sparksql

import org.apache.spark.sql.SparkSession

object SparkSQL {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark SQL")
      .master("local")
      .getOrCreate()
    import spark.implicits._

    val df = spark.read.json("C:\\Users\\11200\\Desktop\\people.json")
    df.show()
    df.printSchema()
    df.select("name").show()
    df.select($"name",$"age"+1).show()
    df.filter($"age">21).show()
    df.groupBy("age").count().show()

    df.createOrReplaceTempView("people")
    val sqlDF = spark.sql("select * from people")
    sqlDF.show()

    df.createGlobalTempView("people")
    spark.sql("select * from global_temp.people").show()

    spark.newSession().sql("select  * from global_temp.people").show()

  //Create DataSet
//    case class Person(name:String,age:Long)
//
//    val caseClassDS = Seq(Person("Andy",18)).toDS()
//    caseClassDS.show()
//
//    val primitiveDS = Seq(1,2,3).toDS()
//    primitiveDS.map(_ + 1).collect()
//
//    val path = "C:\\Users\\11200\\Desktop\\people.json"
//    val peoples = spark.read.json(path).as[Person]
//    peoples.show()

  }






}
