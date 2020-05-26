import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions._
import spark.implicits._


// Define case classes for input data
case class Docword(docId: Int, vocabId: Int, count: Int)
case class VocabWord(vocabId: Int, word: String)

// Read the input data
val docwords = spark.read.
  schema(Encoders.product[Docword].schema).
  option("delimiter", " ").
  csv("hdfs:///user/ashhall1616/bdc_data/assignment/t3/docword-small.txt").
  as[Docword]
val vocab = spark.read.
  schema(Encoders.product[VocabWord].schema).
  option("delimiter", " ").
  csv("hdfs:///user/ashhall1616/bdc_data/assignment/t3/vocab-small.txt").
  as[VocabWord]


// Task 3a:
val result = docwords.groupBy($"vocabId").agg(sum($"count") as "sum(count)").join(vocab,"vocabId").select($"word",$"sum(count)").orderBy($"sum(count)".desc)

result.show()
result.write.csv("file:///home/user13B81A05798322/Task_3a-out")

// Required to exit the spark-shell
sys.exit(0)
