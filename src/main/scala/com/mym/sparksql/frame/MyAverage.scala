package com.mym.sparksql.frame

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

object MyAverage extends UserDefinedAggregateFunction {
  override def inputSchema: StructType = StructType(StructField("inputColum", LongType) :: Nil)

  override def bufferSchema: StructType = {
    StructType(StructField("sum", LongType) :: StructField("count", LongType) :: Nil)
  }

  override def dataType: DataType = DoubleType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = ???

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = ???

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = ???

  override def evaluate(buffer: Row): Any = ???
}
