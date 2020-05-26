// Load the input data and split each line into an array of strings
val twitterLines = sc.textFile("hdfs:///user/ashhall1616/bdc_data/assignment/t2/twitter-small.tsv")
val twitterdata = twitterLines.map(_.split("\t"))

// Task 2b:
// TODO: *** Put your solution here ***
val yearwisedata = twitterdata.map(r=>(r(1).substring(0,4),r(2).toInt)).reduceByKey(_+_).sortBy(_._2,false).first

println(yearwisedata._1 +"\t"+yearwisedata._2)

// Required to exit the spark-shell
sys.exit(0)
