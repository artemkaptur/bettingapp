package com.evolution.bettingapp.components

import org.scalajs.dom

object Common {

  val container = Option(dom.document.getElementById("root")).getOrElse {
    val elem = dom.document.createElement("div")
    elem.id = "root"
    dom.document.body.appendChild(elem)
    elem
  }
}
