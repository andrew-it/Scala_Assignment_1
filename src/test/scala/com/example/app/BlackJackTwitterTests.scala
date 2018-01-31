package com.example.app

import org.scalatra.test.scalatest._

class BlackJackTwitterTests extends ScalatraFunSuite {

  addServlet(classOf[BlackJackTwitter], "/*")

  test("GET / on BlackJackTwitter should return status 200"){
    get("/"){
      status should equal (200)
    }
  }

}
