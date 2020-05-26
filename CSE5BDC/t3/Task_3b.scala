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


// Task 3b:
def firstCharacter(s: String): String = {
  s(0)+""
}

val firstCharacterUDF = udf[String, String](firstCharacter)

val result = docwords.join(vocab,"vocabId").select($"word",$"docId",$"count", firstCharacterUDF(col("word")).as("firstLetter"))

//result.show()
val partitionedResult = result.repartition($"firstLetter")
partitionedResult.write.parquet("file:///home/user13B81A05798322/t3_docword_index_part.parquet")
spark.read.parquet("file:///home/user13B81A05798322/t3_docword_index_part.parquet").show(10)


// Required to exit the spark-shell
sys.exit(0)
