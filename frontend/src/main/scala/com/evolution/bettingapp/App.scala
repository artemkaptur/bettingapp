package com.evolution.bettingapp

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.{a, className, h1, href}
import typings.antd.components._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object AntCSS extends js.Any

@react object App {
  type Props = Unit

  //noinspection ScalaUnusedSymbol
  private val appCss = AppCSS
  //noinspection ScalaUnusedSymbol
  private val antCss = AntCSS

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>

    Layout(className := "App")(
      Layout.Header(className := "App-header")(
        h1(className := "App-title")("Slinky's TODO List")
      ),
      Layout.Content(
//        containers.TodoContainer()
      ),
      Layout.Footer(className := "App-footer")(
        "Check it out on Github: ",
        a(href := "https://github.com/pme123/slinky-todos")(
          "slinky-todos"
        )
      )
    )
  }
}
