package com.example.app

import org.json4s.JsonAST.{JArray, JInt, JObject, JString}
import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json.JacksonJsonSupport

import scala.collection.mutable.{HashMap, ListBuffer}


case class Message(text: String)

class BlackJackTwitter extends ScalatraServlet with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  val listOfMessages: HashMap[Int, Message] = HashMap()


  get("/messages") {
    var sth = ListBuffer[JObject]()
    listOfMessages.toList
    for (message <- listOfMessages.toList) {
      val tmp = JObject(List(
        "id" -> JInt(message._1),
        "text" -> JString(message._2.text)
      ))
      sth += tmp
    }
    render(JArray(sth.toList))
  }

  get("/messages/:id") {
    val id = params("id")
    println("get id")
    if (listOfMessages.contains(id.toInt)) {
      "text" -> listOfMessages(id.toInt).text
    } else {
      NotFound()
    }
  }

  put("/messages/:id") {
    val id = params("id")
    println("put")
    println(parsedBody.extract[Message])
    if (listOfMessages.contains(id.toInt)) {
      listOfMessages(id.toInt) = parsedBody.extract[Message]
      Ok()
    } else {
      NotImplemented()
    }
  }

  delete("/messages/:id") {
    val id = params("id")
    if (listOfMessages.contains(id.toInt)) {
      listOfMessages.remove(id.toInt)
      Ok()
    } else {
      NotImplemented()
    }
  }

  post("/messages/:id") {
    val id = params("id")
    println("post")
    println(parsedBody.extract[Message])
    if (listOfMessages.contains(id.toInt)) {
      NotImplemented()
    } else {
      listOfMessages(id.toInt) = parsedBody.extract[Message]
      Created()
    }
  }
}
