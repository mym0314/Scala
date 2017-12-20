import AssemblyKeys._

assemblySettings

// 这个是打包之后jar包的名字
jarName in assembly := "spark.jar"

// 这个作用是在打包的时候，跳过测试
test in assembly := {}


mergeStrategy in assembly := {
  case PathList(ps@_*) if ps.last endsWith ".properties" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith "UnusedStubClass.class" => MergeStrategy.first
  case "pom.properties" => MergeStrategy.first
  case "UnusedStubClass.class" =>MergeStrategy.first
  case x=>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}