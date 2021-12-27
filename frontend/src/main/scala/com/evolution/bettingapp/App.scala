package com.evolution.bettingapp

import com.evolution.bettingapp.services.AjaxApi.doLogin
import slinky.core.Component
import slinky.core.annotations.react
import slinky.web.html._
import typings.antd.components._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("resources/App.css", JSImport.Default)
@js.native
object AppCSS extends js.Object

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object AntCSS extends js.Any

@react class App extends Component {
  type Props = Unit

  case class State(login: String, password: String)

  override def initialState: State = State("", "")

  //noinspection ScalaUnusedSymbol
  private val appCss = AppCSS
  //noinspection ScalaUnusedSymbol
  private val antCss = AntCSS


  override def render() = {
    Layout(className := "App")(
      Layout.Header(className := "App-header")(
        h1(className := "App-title")("Betting App")
      ),
      Layout.Content(
        div(
          label(
            p("Login"),
            input(`type` := "text", value := state.login, onChange := (e => setState(state.copy(login = e.target.value))))
          ),
          label(
            p("Password"),
            input(`type` := "password", value := state.password, onChange := (e => setState(state.copy(password = e.target.value))))
          ),
          div(
            button("Submit", onClick := doLogin(state.login, state.password))
          )
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
