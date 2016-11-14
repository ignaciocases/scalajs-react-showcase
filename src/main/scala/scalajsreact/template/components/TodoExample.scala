package scalajsreact.template.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

object TodoList {
    
  val component = ReactComponentB[List[String]]("TodoList")
    .render_P { props =>
      def createItem(itemText: String) = <.li(itemText)
      <.ul(props map createItem)
    }
    .build
    
  def apply(items: List[String]) = component(items)
}

object TodoExample {

  def title = "Todo List"

  case class State(items: List[String], text: String)

  class Backend($: BackendScope[Unit, State]) {
    def onChange(e: ReactEventI) = {
      val newValue = e.target.value
      $.modState(_.copy(text = newValue))
    }

    def handleSubmit(e: ReactEventI) =
      e.preventDefaultCB >>
      $.modState(s => State(s.items :+ s.text, ""))

    def render(state: State) =
      <.div(
        <.h3("TODO"),
        Header(),
        TodoList(state.items),
        <.form(^.onSubmit ==> handleSubmit,
          <.input(^.onChange ==> onChange, ^.value := state.text),
          <.button("Add #", state.items.length + 1)
        )
      )
  }

  val component = ReactComponentB[Unit]("TodoApp")
    .initialState(State(Nil, ""))
    .renderBackend[Backend]
    .build

  def apply() = component()

}


object Header {

  def component = ReactComponentB[Unit]("Header")
    .render_P(item =>
      <.p("Fill your todo list!")
    ).build

  def apply() = component()

}
