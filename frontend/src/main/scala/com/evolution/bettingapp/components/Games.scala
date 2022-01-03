package com.evolution.bettingapp.components

import com.evolution.bettingapp.Game
import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html._
import typings.antd.components.Layout

@react object Games {
  case class Props(games: List[Game])

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { props =>

    Layout(className := "App")(
      Layout.Header(className := "App-header")(
        h1(className := "App-title")("Betting App")
      ),
      Layout.Content(
        div(
          h1("List of games" + props.games)
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
