import org.apache.spark.{SparkConf, SparkContext}

object FirstScala {
  def main(args:Array[String]): Unit = {
    new SparkContext(new SparkConf).textFile(args(0)).flatMap(_.split(" ")).filter(_.contains("wor")).map((_,1)).reduceByKey(_ + _).collect().foreach(println)
  }
}
