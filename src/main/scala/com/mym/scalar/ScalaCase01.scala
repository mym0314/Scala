package com.mym.scalar

object ScalaCase01 {

  def main(args: Array[String]): Unit = {

    val  list01 = List(2,3,4,6,8,9)
    val list02 = List(4,5,7,8,9,2)
    val map01 = Map("橘子"->19,"苹果"->21,"香蕉"->12)
    val map02 = Map("橘子"->32,"苹果"->44,"香蕉"->2,"梨香"->43)

   // (0 /: list02)((sum,i)=>{println(sum + "\t"+i);sum + i})

//    (map02 /: map01)((map,kv)=>{
//      println(kv)
//      map + (kv._1->(kv._2+ map.getOrElse(kv._1,0)))
//    })

    map02.filter{case (k,_)=> k.toString.contains("香")}.foreach(println)
    map01.foreach(println)
  }

}
