//spark-shell --jars tika-app-1.25.jar --driver-class-path ~/deps/commons-compress-1.20/commons-compress-1.20.jar

val binRDD = sc.binaryFiles("/lab5/data/")
binRDD.count()
val textRDD = binRDD.map(file => {new org.apache.tika.Tika().detect(file._1)})
val data = textRDD.toDF()
data.show()
data.write.parquet("/lab5/out.parquet")
#binRDD.collect().foreach(f=>{println(f)})
#textRDD.collect().foreach(f=>{println(f)})

# hdfs dfs -ls /lab5/
