package scalajsreact.template

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scalajsreact.template.components.TodoExample

@JSExport
object ReactApp extends JSApp {

  @JSExport
  override def main(): Unit = {
    val mountNode = dom.document.getElementById("root")
    ReactDOM.render(TodoExample(), mountNode)
  }

}