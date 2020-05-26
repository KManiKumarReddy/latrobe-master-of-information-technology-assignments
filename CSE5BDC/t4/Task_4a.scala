import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions._
import spark.implicits._


// Define case classe for input data
case class Article(articleId: Int, title: String, url: String, publisher: String,
                   category: String, storyId: String, hostname: String, timestamp: String)
// Read the input data
val articles = spark.read.
  schema(Encoders.product[Article].schema).
  option("delimiter", ",").
  csv("hdfs:///user/ashhall1616/bdc_data/assignment/t4/news-small.csv").
  as[Article]


// Task 4a:
// Step 1
var storyPublishersDF = articles.dropDuplicates("storyId","publisher").select("storyId","publisher")
storyPublishersDF.write.parquet("file:///home/user13B81A05798322/t4_story_publishers.parquet")


// Step 2
storyPublishersDF.groupBy($"storyId").agg(count($"publisher").as("count")).orderBy($"count".desc).filter($"count" >= 5).limit(10).foreach(println(_))


// Required to exit the spark-shell
sys.exit(0)
