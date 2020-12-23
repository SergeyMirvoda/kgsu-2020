//Извлечение структурированных данных из неструктурированных
//spark-shell --jars tika-app-1.25.jar --driver-class-path ~/deps/commons-compress-1.20/commons-compress-1.20.jar
import org.apache.tika._
import org.apache.tika.metadata._
import org.apache.tika.parser.ParseContext._



val binRDD = sc.binaryFiles("/lab5/data/")

binRDD.count()

val textRDD = binRDD.map(file => {new org.apache.tika.Tika().detect(file._1)})

binRDD.collect().foreach(f=>{println(f)})

//https://docs.scala-lang.org/tour/tuples.html

//https://docs.scala-lang.org/tour/packages-and-imports.html




textRDD.toDF.write.format("orc").save("/lab5/out4")
textRDD.saveAsTextFile("/tika_out/")




val second = binRDD.take(2)(1)
val secondPath = new org.apache.hadoop.fs.Path(second._1)
val data = fs.open(secondPath)

val c = new ParseContext()
val meta = new Metadata()
val h = new org.apache.tika.sax.BodyContentHandler()
val p = new org.apache.tika.parser.image.ImageParser()
val p = new org.apache.tika.parser.jpeg.JpegParser()
p.parse(data, h, meta, c)
meta.names().foreach(n=>printn(meta.get(n))
