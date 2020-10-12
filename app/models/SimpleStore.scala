package models

import scala.collection.{immutable, mutable}

case class SpaceShip(timeOfArrival: Int, handleTime: Int)

case class HangarSpec(numberOfPlaces: Int)

abstract class ShortcutStore {
  def add(shortcut: Shortcut): Unit

  def search(path: String): Set[Shortcut]
}

object ShortcutParser extends ShortcutStore {
  private val store = mutable.HashMap[String, mutable.Set[Shortcut]]()

  def add(shortcut: Shortcut): Unit = {
    if (store.contains(shortcut.action.path))
      store(shortcut.action.path) += shortcut
    else store += shortcut.action.path -> mutable.Set(shortcut)
  }

  def search(path: String): Set[Shortcut] = {
    if (store.contains(path)) {
      store(path).toSet
    } else Set[Shortcut]()
  }
}