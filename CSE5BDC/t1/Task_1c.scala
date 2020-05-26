// Load the input data and split each line into an array of strings
val vgdataLines = sc.textFile("hdfs:///user/ashhall1616/bdc_data/assignment/t1/vgsales-small.csv")
val vgdata = vgdataLines.map(_.split(";"))

// Task 1c:
val salesData = vgdata.map(r=>(r(3),r(5).toFloat+r(6).toFloat+r(7).toFloat)).reduceByKey(_+_)
val highest = salesData.sortBy(_._2,false).first
val lowest = salesData.sortBy(_._2).first

println("Highest selling Genre: "+highest._1+" \tGlobal Sale (in millions): " + highest._2)
println("Lowest selling Genre: "+lowest._1+" \tGlobal Sale (in millions): " + lowest._2)


// Required to exit the spark-shell
sys.exit(0)