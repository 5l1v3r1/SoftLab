package demo

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.
{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.
{DataType, DoubleType, StructType}
/**
Created by damatya on 4/3/17.
  */
class TestUdaf (inputSch:StructType) extends UserDefinedAggregateFunction{

  override def inputSchema: StructType = inputSch
  //inputSchema = inputSch
  override def bufferSchema: StructType = new StructType()
    .add("totalRxPaid",DoubleType)
    .add("totalRxAllowedAmt",DoubleType)

  override def dataType: DataType = new StructType()
    .add("totalRxPaid",DoubleType)
    .add("totalRxAllowedAmt",DoubleType)

  override def deterministic: Boolean = false

  override def initialize(buffer: MutableAggregationBuffer): Unit =
  {
    buffer.update(0,0D)
    buffer.update(1,0D)
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    var paidAmount : Float = 0f
    var allowedAmount : Float = 0f

    try {
      paidAmount=input.getFloat(1)
      allowedAmount=input.getFloat(2)
    }
    catch
      {
        case e:Exception => println ("invalid amount")
      }

    val totalPaidAmount = buffer.getDouble(0)+paidAmount
    val totalAllowedAmount = buffer.getDouble(1)+allowedAmount
    buffer.update(0,totalPaidAmount)
    buffer.update(1,totalAllowedAmount)
  }
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    buffer1.update(0,buffer1.getDouble(0)+buffer2.getDouble(0))
    buffer1.update(1,buffer1.getDouble(1)+buffer2.getDouble(1))
  }

  override def evaluate(buffer: Row): Any = {
    (buffer.getDouble(0) , buffer.getDouble(1))
  }
}