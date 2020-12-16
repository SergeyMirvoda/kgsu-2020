val textFile = spark.read.textFile("/lab4/Alice.txt")
textFile.count()
//3773


//Найдём все строки с именем Alice 
textFile.filter(line => line.contains("Alice")).count()  
//399


 val wordCounts = textFile.flatMap(line => line.split(" ")).groupByKey(identity).count()

wordCounts.collect()

wordCounts.show()


val sorted = wordCounts.orderBy(desc("count(1)"))

sorted.show()
