package load2raw
import org.apache.spark.sql.SparkSession
object Kafka_Producer {
  def main(args: Array[String]): Unit = {
    val sparksession = SparkSession
      .builder()
      .appName("kafka_producer")
      .master("local")
      .enableHiveSupport()
      .getOrCreate()

  }
}
