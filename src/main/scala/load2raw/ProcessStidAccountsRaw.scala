package load2raw
import common.Configuration._
import common.Schemas._
import functions.Functions._
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
object ProcessStidAccountsRaw {
  def main(args: Array[String]): Unit = {
    val sparksession = SparkSession.builder.appName("kafkaspark").enableHiveSupport().getOrCreate()
    val ssc = new StreamingContext(sparksession.sparkContext, Seconds(2))
    val status = consumeTopic(KAFKA_STIDACCOUNTS_TOPIC,stidaccountsschema,HIVE_DATABASE,HIVE_STIDACCOUNTS_RAW_TABLE,ssc,sparksession)
    if(status){
      ssc.start()
      ssc.awaitTermination()
    }
  }
}