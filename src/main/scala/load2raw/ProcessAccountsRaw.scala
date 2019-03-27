package load2raw
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import common.Configuration._
import common.Schemas._
import functions.Functions._
object ProcessAccountsRaw {
  def main(args: Array[String]): Unit = {
    val sparksession = SparkSession.builder.appName("kafkaspark").enableHiveSupport().getOrCreate()
    val ssc = new StreamingContext(sparksession.sparkContext, Seconds(2))
    val status = consumeTopic(KAFKA_ACCOUNTS_TOPIC,accountsschema,HIVE_DATABASE,HIVE_ACCOUNTS_RAW_TABLE,ssc,sparksession)
    if(status){
      ssc.start()
      ssc.awaitTermination()
    }
  }
}