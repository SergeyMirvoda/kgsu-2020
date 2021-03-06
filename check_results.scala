val names = Seq("file", "content")
val rdd = spark.sparkContext.wholeTextFiles("/exam/")
val data = rdd.map((x) => {(x._1 , x._2.split("\n"))} ).toDF(names: _*)


val dt1 = data.withColumn("group", when($"content".getItem(0).rlike("\\d{5}"), $"content".getItem(0)).otherwise("не указано"))
val sorted = dt1.orderBy(asc("group"))
sorted.show()
/*
+--------------------+--------------------+----------------+
|                file|             content|           group|
+--------------------+--------------------+----------------+
|hdfs://0.0.0.0:90...|[ИТ - 40917, , Мо...|      ИТ - 40917|
|hdfs://0.0.0.0:90...|[ИТ - 50916, Стар...|      ИТ - 50916|
|hdfs://0.0.0.0:90...|[ИТ-40917, Сапрон...|        ИТ-40917|
|hdfs://0.0.0.0:90...|[ИТ-40917, Вороно...|        ИТ-40917|
|hdfs://0.0.0.0:90...|[ИТ-40917, Вороно...|        ИТ-40917|
|hdfs://0.0.0.0:90...|[ИТ-50916, Кураже...|        ИТ-50916|
|hdfs://0.0.0.0:90...|[Галисултанов Рус...|      не указано|
|hdfs://0.0.0.0:90...|[�Группа ИТ-40917...|�Группа ИТ-40917|
+--------------------+--------------------+----------------+
*/

val rows = sorted.select($"group", explode($"content"))
/*
+----------+--------------------+
|     group|                 col|
+----------+--------------------+
|ИТ - 40917|          ИТ - 40917|
|ИТ - 40917|                    |
|ИТ - 40917|Иван Алек...|
|ИТ - 40917|https://github.co...|
|ИТ - 40917|...|
|ИТ - 40917|https://github.co...|
|ИТ - 40917|ри...|
|ИТ - 40917|https://github.co...|
|ИТ - 40917| Максим С...|
|ИТ - 40917|https://github.co...|
|ИТ - 50916|          ИТ - 50916|
|ИТ - 50916| Иван ...|
|ИТ - 50916|https://github.co...|
|ИТ - 50916| Константи...|
|ИТ - 50916|https://github.co...|
*/


rows.write.format("csv").save("/exam_results/")


//Прочитаем результаты
//hdfs dfs -text /exam_results/*.csv




