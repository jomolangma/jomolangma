package com.jomolangma.app.scala.actor

/**
  * Created by ZhangLijun on 5/10/16.
  */
object TeacherProtocol{
  case class QuoteRequest()
  case class QuoteResponse(quoteString:String)
}

