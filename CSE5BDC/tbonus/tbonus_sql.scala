import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions._
import spark.implicits._


// Define case classe for input data
case class Hashtag(tokenType: String, month: String, count: Int, hashtagName: String)
// Read the input data
val hashtags = spark.read.
  schema(Encoders.product[Hashtag].schema).
  option("delimiter", "\t").
  csv("hdfs:///user/ashhall1616/bdc_data/assignment/t2/twitter.tsv").
  as[Hashtag]


// Bonus task:
val result = hashtags.as("hashtags1").join(hashtags.as("hashtags2"), $"hashtags1.hashtagName".equalTo($"hashtags2.hashtagName") && ($"hashtags1.month".cast("int").equalTo($"hashtags2.month".cast("int").minus(1)) || $"hashtags1.month".cast("int").equalTo($"hashtags2.month".cast("int").minus(89)))).orderBy($"hashtags1.count".minus($"hashtags2.count")).limit(1).select($"hashtags1.hashtagName",$"hashtags1.month",$"hashtags2.month",$"hashtags1.count",$"hashtags2.count").first

println("Hashtag name: "+result(0))
println("count of month "+result(1)+": "+result(3))
println("count of month "+result(2)+": "+result(4))

// NOTE: You only need to complete either the SQL *OR* RDD task to get the bonus marks


// Required to exit the spark-shell
sys.exit(0)
