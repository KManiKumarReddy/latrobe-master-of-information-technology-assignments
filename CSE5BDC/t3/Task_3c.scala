import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions._
import spark.implicits._


val queryWords = scala.io.StdIn.readLine("Query words: ").split(" ")


// Task 3c:
val data = spark.read.parquet("file:///home/user13B81A05798322/t3_docword_index_part.parquet")
for(queryWord <- queryWords) {
    val result = data.filter($"firstLetter" === (queryWord(0)+"")).filter($"word" === queryWord).orderBy($"count".desc).select($"word",$"docId")
    if(result.count()>0) {
        println(result.first)
    }
}


// Required to exit the spark-shell
sys.exit(0)
