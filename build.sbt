name := "Spark"

version := "0.1"

scalaVersion := "2.11.4"


libraryDependencies+="org.apache.spark"%"spark-core_2.11"%"2.2.0"
libraryDependencies+="org.apache.hadoop"%"hadoop-client"%"2.7.2"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.2.0"
libraryDependencies+="org.apache.spark"%"spark-sql_2.11"%"2.2.0"
        