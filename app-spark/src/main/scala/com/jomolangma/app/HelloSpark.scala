package com.jomolangma.app

import org.apache.spark.{SparkContext, SparkConf}

/**
 * Created by zhanglijun on 6/25/15.
 */
class HelloSpark {

}
object HelloSpark {

  def main (args: Array[String]) {
    if(args.length != 2){
      System.err.println("Usage:HelloSpark <Input> <Output>")
      System.exit(1)
    }

    val conf = new SparkConf().setAppName("HelloSpark")
    val sc = new SparkContext(conf)

    sc.textFile(args(0)).map(_.split("\t")).filter(_.length == 6).map(x=>(x(1),1)).reduceByKey(_+_).
    map(x=>(x._2,x._1)).sortByKey(false).map(x=>(x._2,x._1)).saveAsTextFile(args(1))

    sc.stop()
  }
}
