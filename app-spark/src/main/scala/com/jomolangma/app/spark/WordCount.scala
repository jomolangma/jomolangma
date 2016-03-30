package com.jomolangma.app.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by ZhangLijun on 2015/12/18.
 */
object WordCount {
  def main(args: Array[String]){
    if(args.length != 2){
      println("usage: <input> <output>")
      return
    }

    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    val textFile  = sc.textFile(args(0))
    val result = textFile.flatMap(_.split(" "))
      .map(word => (word, 1)).reduceByKey(_ + _)
    result.saveAsTextFile(args(1))

    sc.stop()
  }
}
