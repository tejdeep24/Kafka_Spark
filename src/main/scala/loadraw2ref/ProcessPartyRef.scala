package loadraw2ref
import org.apache.spark.sql.SparkSession
import functions.Functions._
import common.Configuration._
object ProcessPartyRef {
  def main(args: Array[String]): Unit = {
    val sparksession = SparkSession.builder.appName("kafkaspark").enableHiveSupport().getOrCreate()
    loadRawToRefined(HIVE_DATABASE,HIVE_PARTY_RAW_TABLE,HIVE_PARTY_REFINED_TABLE,sparksession)
  }
}