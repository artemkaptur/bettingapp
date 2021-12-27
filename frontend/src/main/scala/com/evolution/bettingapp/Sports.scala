package com.evolution.bettingapp

import slinky.core.FunctionalComponent
import slinky.core.annotations.react
import slinky.web.html.{div, h1}
import typings.antd.components.Layout

@react object Sports {
  type Props = Unit

  val component: FunctionalComponent[Props] = FunctionalComponent[Props] { _ =>

    Layout.Content(
      div(
        h1("The main page of app")
      )
    )

  }
}
