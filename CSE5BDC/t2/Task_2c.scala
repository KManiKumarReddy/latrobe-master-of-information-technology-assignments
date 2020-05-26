// Load the input data and split each line into an array of strings
val twitterLines = sc.textFile("hdfs:///user/ashhall1616/bdc_data/assignment/t2/twitter-small.tsv")
val twitterdata = twitterLines.map(_.split("\t"))

// Task 2c:
// Remember that each month is a string formatted as YYYYMM
val x = scala.io.StdIn.readLine("x month: ")
val y = scala.io.StdIn.readLine("y month: ")
val tweetcount = twitterdata.map(r=>(r(3),r(2).toInt,r(1)))
val xtweets = tweetcount.filter(_._3 == x).map(r=>(r._1,r._2))
val ytweets = tweetcount.filter(_._3 == y).map(r=>(r._1,r._2))

val result = xtweets.join(ytweets).filter(a=>a._2._1 > 0 && a._2._1 > 0).reduceByKey((a,b)=>(a._1+b._1,a._2+b._2)).sortBy(a=>a._2._2-a._2._1,false).first

println("hashtagName: "+result._1+", countX: "+result._2._1+", countY: "+result._2._2)


// Required to exit the spark-shell
sys.exit(0)
