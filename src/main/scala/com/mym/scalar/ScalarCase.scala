package com.mym.scalar

object ScalarCase {

  def main(args: Array[String]): Unit = {
    println(matchText(3))
    println(matchAny("one"))
    println(matchAny(3))
    println(matchAny("haizei"))
    println(matchAny(1))

  }

  def  matchText(x:Int):String = x match{
    case 1 => "One"
    case 2 =>"two"
    case 3 =>"three"
  }

  def  matchAny(x : Any):Any = x match{
    case 1 =>  1000
    case "one" => "This is one"
    case y:Int =>'s'
    case _=>"manay"

  }

}
