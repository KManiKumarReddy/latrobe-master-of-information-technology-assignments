import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions._
import spark.implicits._


// Task 4b:
val storyPublishersDF = spark.read.parquet("file:///home/user13B81A05798322/t4_story_publishers.parquet")

//storyPublishersDF.join(storyPublishersDF,"storyId").show

val result = storyPublishersDF.as("storyPublishers1").join(storyPublishersDF.as("storyPublishers2"), $"storyPublishers1.storyId".equalTo($"storyPublishers2.storyId") && $"storyPublishers1.publisher" > $"storyPublishers2.publisher").groupBy($"storyPublishers1.publisher",$"storyPublishers2.publisher").count().orderBy($"count".desc)

result.show()

result.select($"storyPublishers1.publisher".as("publisher1"),$"storyPublishers2.publisher".as("publisher2"),$"count").write.csv("file:///home/user198764048322/t4_paired_publishers.csv")
// Required to exit the spark-shell
sys.exit(0)
