package common
import java.io.File
import com.typesafe.config.{Config, ConfigFactory}
object Configuration {
  def get_config(): Config = {
    ConfigFactory.parseFile(new File("config/consumer.properties"))
  }
  val config: Config = get_config()
  val APP_RUN_MODE: String = config.getString("app.run.mode")

  //Kafka Parameters
  val KAFKA_BROKER: String = config.getString("app.kafka.broker.servers")
  val KAFKA_PARTYID_TOPIC: String = config.getString("app.kafka.partyid.topic")
  val KAFKA_ACCOUNTS_TOPIC: String = config.getString("app.kafka.accounts.topic")
  val KAFKA_OPPORLINEITEM_TOPIC: String = config.getString("app.kafka.opportunitylineitem.topic")
  val KAFKA_OPP_TOPIC: String = config.getString("app.kafka.opportunity.topic")
  val KAFKA_STIDACCOUNTS_TOPIC: String = config.getString("app.kafka.stidaccounts.topic")
  val KAFKA_SECURITY_PROTOCOL: String = config.getString("app.kafka.security.protocol")
  val KAFKA_TRUSTSTORE_LOCATION: String = config.getString("app.kafka.truststore.location")
  val KAFKA_TRUSTSTORE_PASSWORD: String = config.getString("app.kafka.truststore.password")
  val KAFKA_KEYSTORE_LOCATION: String = config.getString("app.kafka.keystore.location")
  val KAFKA_KEYSTORE_PASSWORD: String = config.getString("app.kafka.keystore.password")
  val KAFKA_KEY_PASSWORD: String = config.getString("app.kafka.key.password")
  val KAFKA_GROUPID:String = config.getString("app.kafka.groupid")
  val KAFKA_POLLTIMEOUT:String = config.getString("app.kafka.pollTimeout")
  val KAFKA_OFFSET:String = config.getString("app.kafka.offsetReset")
  //Hive Parameters
  val HIVE_DATABASE: String = config.getString("app.hive.database")
  val HIVE_PARTY_RAW_TABLE:String = config .getString("app.hive.party.rawtable")
  val HIVE_PARTY_REFINED_TABLE:String = config.getString("app.hive.party.refinedtable")
  val HIVE_ACCOUNTS_RAW_TABLE:String = config.getString("app.hive.accounts.rawtable")
  val HIVE_ACCOUNTS_REFINED_TABLE:String = config.getString("app.hive.accounts.refinedtable")
  val HIVE_OPPORTUNITYLINEITEM_RAW_TABLE:String = config.getString("app.hive.opportunitylineitem.rawtable")
  val HIVE_OPPORTUNITYLINEITEM_REFINED_TABLE:String = config.getString("app.hive.opportunitylineitem.refinedtable")
  val HIVE_OPPORTUNITY_RAW_TABLE:String = config.getString("app.hive.opportunity.rawtable")
  val hIVE_OPPORTUNITY_REFINED_TABLE:String = config.getString("app.hive.opportunity.refinedtable")
  val HIVE_STIDACCOUNTS_RAW_TABLE:String = config.getString("app.hive.stidaccounts.rawtable")
  val HIVE_STIDACCOUNTS_REFINED_TABLE:String = config.getString("app.hive.stidaccounts.refinedtable")
}