package com.mym.scalar

object ScalarCaseClass {

  case class Person(name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val haizei =  Person("haizei", 20)
    val sishen =  Person("sishen", 18)
    val yiquan =  Person("yiquan", 19)
    val yaojing =  Person("yaojingdeweiba", 23)

    for (person <- List(haizei,sishen, yiquan ,yaojing)) {
      person match {
        case Person("haizei", 20) => println("喜欢海贼王")
        case Person("sishen", 18) => println("喜欢死神")
        case Person("yiquan", 19) => println("喜欢一拳超人")
        case Person(name, age) => println(
          "Age: " + age + " year, name: " + name + "?")
      }
    }
  }



}
