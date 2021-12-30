package com.evolution.bettingapp

import com.evolution.bettingapp.components.Common.container
import slinky.hot
import slinky.web.ReactDOM

import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import scala.scalajs.{LinkingInfo, js}

@JSImport("resources/index.css", JSImport.Default)
@js.native
object IndexCSS extends js.Object

object Main {
  //noinspection ScalaUnusedSymbol
  //  private val css = IndexCSS

  @JSExportTopLevel("main")
  def main(): Unit = {
    if (LinkingInfo.developmentMode) {
      hot.initialize()
    }

    ReactDOM.render(App(), container)
    ()
  }
}
