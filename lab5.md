# Лабораторная 5. Основы HDFS

> Цель: познакомиться с основными возможностями клиентских утилит hadoop.

## Задание

1. Установить библиотеки hadoop  
1.1 ``wget https://archive.apache.org/dist/hadoop/core/hadoop-2.8.5/hadoop-2.8.5.tar.gz``  
1.2 Установите пути в файл ``~/.bashrc``  
```
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export HADOOP_HOME=$HOME/hadoop-2.8.5
export HADOOP_HDFS_HOME=$HADOOP_HOME
export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin
```
1.3 выполните команду ``hdfs version``  
Должна вернуться версия Hadoop ``2.8.5`` и параметры сборки  

1.4 Изучите команды утилиты ``hdfs``  
1.4.1 Начните, например с ``ls`` и получите список файлов в папке lab4 на удалённом сервере  
> В глубинах папки ``lab4`` находятся результаты выполнения задачи ``Apache Spark``, которая рассчитала количество упоминаний слов в файле Alice.txt  
```
 hdfs dfs -ls hdfs://192.168.121.147:9000/lab4/
```
1.5 Чтение файлов из кластера HDFS  
1.5.1 С помощью многократного вызова команды ``ls`` найдите файл ``part-r-00000`` внутри папки ``lab4``  
1.5.2 Повторите то же самое с помощью команды ``lsr`` https://hadoop.apache.org/docs/r2.4.1/hadoop-project-dist/hadoop-common/FileSystemShell.html#lsr  
1.5.3 С помощью команды ``cat`` прочитайте содержимое файла ``part-r-00000`` находящегося внутри папки lab4. Что вы видите? Можно ли быстро найти самое часто встречающееся слово?  
1.5.4 С помощью интерпретатора ``awk`` разбейте содержимое файла ``part-r-00000`` на колонки с разделителем ``:``, например так:  
```
awk '{print $1":"$2}'
```
> команды unix можно объединять через символ``|``, например
```
#найдём все the в тексте
hdfs dfs -cat hdfs://192.168.121.147:9000/lab4/ | grep the 
```

1.5.5 Добавьте численную сортировку с разделителем ``:`` по второму полю в котором число упоминаний  
```
 | sort -t: -nk2
```
1.5.6 Если вы всё сделали правильно, то в результате самым часто упоминаемым словом должен стать артикль ``the`` с рультатом 1677 вхождений.  
1.5.7 Воспользовавшись командой ``grep`` найдите сколько раз встречается имя Alice.  
1.5.8 Что мешает получить корректный ответ?  
1.5.9 Модифицируйте код awk, для удаления знаков препинания, повторите поиск  

1.6 Запись файлов  
1.6.1 Создайте на диске файл с любым уникальным названием и запишите туда состав группы, которой вы выполняли лабораторные  
```
Группа
ФИО1
ссылка на гитхаб1
ФИО2
ссылка на гитхаб2
...
```
1.6.2 С помощью команды ``put`` запишите этот файл в папку ``/exam/``  
1.6.3 Запишите этот же файл в папку /lab4/. Удалось? Почему? см. https://hadoop.apache.org/docs/r2.4.1/hadoop-project-dist/hadoop-hdfs/HdfsPermissionsGuide.html  











