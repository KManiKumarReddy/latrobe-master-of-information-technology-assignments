// Load the input data and split each line into an array of strings
val twitterLines = sc.textFile("hdfs:///user/ashhall1616/bdc_data/assignment/t2/twitter.tsv")
val twitterdata = twitterLines.map(_.split("\t"))


// Bonus task:
val hashtags = twitterdata.map(r=>(r(3),(r(1).toInt,r(2).toInt)))
val result = hashtags.join(hashtags).filter(a=>(a._2._1._1 == (a._2._2._1 - 1) || a._2._1._1 == (a._2._2._1 - 89))).sortBy(a=>a._2._1._2-a._2._2._2).first

println("Hashtag name: "+result._1)
println("count of month "+result._2._1._1+": "+result._2._1._2)
println("count of month "+result._2._2._1+": "+result._2._2._2)

// NOTE: You only need to complete either the SQL *OR* RDD task to get the bonus marks


// Required to exit the spark-shell
sys.exit(0)
