package functions
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.sql.types.StructType
import common.Configuration._
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
object Functions {
  def getKafkaParms():Map[String,String]={
    val kafkaParams = Map[String, String](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> KAFKA_BROKER,
      ConsumerConfig.GROUP_ID_CONFIG -> KAFKA_GROUPID,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> KAFKA_OFFSET,
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> "false",
      "spark.kafka.poll.time" -> KAFKA_POLLTIMEOUT,
      "security.protocol" -> KAFKA_SECURITY_PROTOCOL,
      "ssl.truststore.location" -> KAFKA_TRUSTSTORE_LOCATION,
      "ssl.truststore.password" -> KAFKA_TRUSTSTORE_PASSWORD,
      "ssl.keystore.location" -> KAFKA_KEYSTORE_LOCATION,
      "ssl.keystore.password" -> KAFKA_KEYSTORE_PASSWORD,
      "ssl.key.password" -> KAFKA_KEY_PASSWORD
    )
    return kafkaParams
  }

  def consumeTopic(topic:String,Schema:StructType,db:String,table:String,ssc:StreamingContext,sparksession:SparkSession):Boolean={
    val topicsSet = topic.split(",").toSet
    val consumerStrategy = ConsumerStrategies.Subscribe[String, String](topicsSet,getKafkaParms())
    val messages = KafkaUtils.createDirectStream[String, String](
      ssc, LocationStrategies.PreferConsistent, consumerStrategy
    )
    messages.map(record=>record.value()).foreachRDD{rdd=>
      if(!rdd.isEmpty()) {
        val PartyDf = sparksession.read.schema(Schema).json(rdd)
        PartyDf.show(2)
        //PartyDf.write.mode("append").format("csv").insertInto(s"$db.$table")
      }
    }
    return true
  }

  def loadRawToRefined(db:String,srctable:String,desttable:String,sparksession:SparkSession)={
    sparksession.sql(
      s"INSERT OVERWRITE TABLE $db.$desttable " +
        "SELECT " +
        "party_id,prty_assc_typ_cd,prty_assc_vl,prty_assc_stts_cd,prty_assc_vl_typ_cd,last_update_date " +
        "FROM " +
        "(SELECT " +
        "*,ROW_NUMBER() over (partition by party_id,prty_assc_vl,prty_assc_vl_typ_cd order by last_update_date desc) as rank " +
        s"FROM $db.$srctable) rank_stg " +
        "where rank=1")
  }
}