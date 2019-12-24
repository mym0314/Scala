name := "Spark"

version := "0.1"

scalaVersion := "2.11.12"


libraryDependencies+="org.apache.spark"%"spark-core_2.11"%"2.4.4"
libraryDependencies+="org.apache.hadoop"%"hadoop-client"%"2.7.4"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.4.4"
libraryDependencies+="org.apache.spark"%"spark-sql_2.11"%"2.4.4"
libraryDependencies+="org.apache.kafka"%"kafka-clients"%"1.0.0"
libraryDependencies+="org.apache.spark"%"spark-streaming-kafka-0-8_2.11"%"2.4.4"
libraryDependencies+="org.scalanlp"%"breeze_2.11"%"0.12"

libraryDependencies+="org.apache.poi"%"poi-ooxml"%"3.14"
libraryDependencies+="org.apache.poi"%"poi"%"3.14"





