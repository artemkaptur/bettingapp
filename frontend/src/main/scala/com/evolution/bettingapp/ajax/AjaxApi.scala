package com.evolution.bettingapp.ajax

import com.evolution.bettingapp.App
import com.evolution.bettingapp.components.Common.container
import com.evolution.bettingapp.components.Sports
import org.scalajs.dom.{XMLHttpRequest, window}
import org.scalajs.dom.ext.Ajax
import slinky.web.ReactDOM

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object AjaxApi {

  def doLogin(login: String, password: String): () => Unit = { () =>
    Ajax.post("http://localhost:8883/login",
      s"{\"login\":\"$login\",\"password\":\"$password\"}")
      .onComplete {
        case Success(xhr: XMLHttpRequest) =>
          println("1------------------->" + xhr.responseText)
          ReactDOM.render(Sports(), container)
        case Failure(ex)                  =>
          println("2------------------->" + ex)
          window.alert("Invalid credentials")
          ReactDOM.render(App(), container)
      }
  }
}