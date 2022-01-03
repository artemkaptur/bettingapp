package com.evolution.bettingapp.ajax

import com.evolution.bettingapp.App
import com.evolution.bettingapp.Game
import com.evolution.bettingapp.components.Common.container
import com.evolution.bettingapp.components.{Games, Sports}
import io.circe.Json
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.{XMLHttpRequest, window}
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

  def getGames(game: String): () => Unit = { () =>
    Ajax.get(s"http://localhost:8883/games?gameType=$game")
      .onComplete {
        case Success(xhr: XMLHttpRequest) =>
          val games = Json.fromString(xhr.response.toString).as[List[Game]]
          for{
            games2 <- games
          } yield ReactDOM.render(Games(games2), container)
//          games match {
//            case Json.Null => window.alert("Something went wrong")
//            case games         => ReactDOM.render(Games(games), container)
//          }
        case Failure(ex)                  =>
          println("2------------------->" + ex)
          window.alert("Something went wrong")
          ReactDOM.render(Sports(), container)
      }
  }
}
