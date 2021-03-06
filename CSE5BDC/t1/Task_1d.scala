// Load the input data and split each line into an array of strings
val vgdataLines = sc.textFile("hdfs:///user/ashhall1616/bdc_data/assignment/t1/vgsales-small.csv")
val vgdata = vgdataLines.map(_.split(";"))


// Task 1d:
val total = vgdata.count
val marketShare = vgdata.map(r=>(r(4),1)).reduceByKey(_+_).mapValues(_.toFloat/total*100).sortBy(_._2,false).take(50)

marketShare.foreach(println)


// Required to exit the spark-shell
sys.exit(0)
