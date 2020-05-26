import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.expressions._
import spark.implicits._


val docIds = scala.io.StdIn.readLine("Document IDs: ").split(" ")


// Task 3d:
val data = spark.read.parquet("file:///home/user13B81A05798322/t3_docword_index_part.parquet").repartition($"docId")
for(docId <- docIds) {
    val result = data.filter($"docId" === docId).orderBy($"count".desc).select($"docId",$"word",$"count")
    if(result.count()>0) {
        println(result.first)
    }
}


// Required to exit the spark-shell
sys.exit(0)
