package com.mym.rdd

import java.io.File
import scala.io.Source._
import org.apache.spark.sql.SparkSession

object DFSReadWriteTest {
  private var localFilePath: File = new File(".")
  private var dfsDirPath: String = ""

  private val NPARAMS = 2

  private def readFile(filename: String): List[String] = {
    val lineIter: Iterator[String] = fromFile(filename).getLines()
    val lineList: List[String] = lineIter.toList
    lineList
  }

  private def printUsage(): Unit = {
    val usage: String = "DFS Read-Write Test\n" +
      "\n" +
      "Usage: localFile dfsDir\n" +
      "\n" +
      "localFile - (string) local file to use in test\n" +
      "dfsDir - (string) DFS directory for read/write tests\n"
    println(usage)
  }

  private def parseArgs(args: Array[String]): Unit = {
    println(args.length + "*****"+args(0)+"*****"+args(1))
    if (args.length != NPARAMS) {
      printUsage()
      System.exit(1)
    }

    var i = 0

    localFilePath = new File(args(0))
    if (!localFilePath.exists) {
      println("Given path (" + args(i) + ") does not exist.\n")
      printUsage()
      System.exit(1)
    }

    if (!localFilePath.isFile) {
      System.err.println("Given path (" + args(i) + ") is not a file.\n")
      printUsage()
      System.exit(1)
    }

    i += 1
    dfsDirPath = args(1)
  }

  def runLocalWordCount(fileContents: List[String]): Int = {
    fileContents
      .flatMap(_.split(" "))
      .flatMap(_.split("\t"))
      .filter(_.nonEmpty )
      .groupBy(w => w)
      .mapValues(_.size)
      .values
      .sum
  }

  def main(args: Array[String]): Unit = {
    parseArgs(args)

    println("Performing local word count")
    val fileContents = readFile(localFilePath.toString)
    val localWordCount = runLocalWordCount(fileContents)

    println("Creatin SparkSession")
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("DFSReadWrite Test")
      .getOrCreate()

    println("Writing local file to DFS")
    val dfsFilename = "hdfs://mycluster/user/root"+ "/dfs_read_write_test1"
    val fileRDD = spark.sparkContext.parallelize(fileContents)
    fileRDD.saveAsTextFile(dfsFilename)

    val readFileRDD = spark.sparkContext.textFile(dfsFilename)

    val dfsWordCount = readFileRDD
      .flatMap(_.split(" "))
      .flatMap(_.split("\t"))
      .filter(_.nonEmpty) 
      .map(w => (w, 1))
      .countByKey()
      .values
      .sum
    spark.stop()

    if (localWordCount == dfsWordCount) {
      println(s"Success! Local Word Count ($localWordCount) " +
        s"and DFS Word Count ($dfsWordCount) agree.")
    } else {
      println(s"Failure! Local Word Count ($localWordCount) " +
        s"and DFS Word Count ($dfsWordCount) disagree.")
    }
  }

}
