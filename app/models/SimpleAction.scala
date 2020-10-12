package models

class SimpleAction(val path: String, val action: String) {

  override def toString: String = path+"."+action
}

object SimpleAction {
  def apply(path: String, action: String): SimpleAction = new SimpleAction(path, action)

  def apply(fullpath: String): SimpleAction = {
    val splitdata = fullpath.split("[.]")
    if (splitdata.length == 2) {
      if (splitdata.head.nonEmpty && splitdata.last.nonEmpty)
        new SimpleAction(splitdata.head, splitdata.last)
      else throw new IllegalArgumentException("Неверный аргумент для действия!")
    } else throw new IllegalArgumentException("Неверный аргумент для действия!")
  }
}