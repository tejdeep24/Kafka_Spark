name := "Kafka_Spark"
version := "0.1"
scalaVersion := "2.11.8"
val sparkVer = "2.2.0"
libraryDependencies ++= {
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVer % "provided",
    "org.apache.spark" %% "spark-streaming" % sparkVer % "provided",
    "org.apache.spark" %% "spark-sql" % sparkVer % "provided",
    "org.apache.spark" %% "spark-hive" % sparkVer % "provided"
  )
}
libraryDependencies ++=  {
  Seq(
    "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.1.2",
    "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.2.0",
    "org.apache.kafka" % "kafka-clients" % "0.10.0.1",
    // Properties file reader (Apache 2.0)
    "com.typesafe" % "config" % "1.3.1",
    // For Fuzzy logic ()
    "org.apache.commons" % "commons-text" % "1.0"
  )
    .map(_.excludeAll(
      ExclusionRule(organization = "org.scalacheck"),
      ExclusionRule(organization = "org.scalactic"),
      ExclusionRule(organization = "org.scalatest")
    ))
}