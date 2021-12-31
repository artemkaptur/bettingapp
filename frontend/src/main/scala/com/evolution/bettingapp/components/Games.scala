package com.evolution.bettingapp.components

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._
import typings.antd.components.Layout

@react object Games {
  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>

    Layout(className := "App")(
      Layout.Header(className := "App-header")(
        h1(className := "App-title")("Betting App")
      ),
      Layout.Content(
        div(
          h1("List of games")
        )
      ),
      Layout.Footer(className := "App-footer")(
        "Check it out on Github: ",
        a(href := "https://github.com/artemkaptur/bettingapp")(
          "Betting App"
        )
      )
    )
  }
}
