package models

import scala.collection.mutable.ListBuffer

class SpecKey(var Shift:Boolean = false,var Control:Boolean = false,var Alt:Boolean = false) {
  override def toString: String = {
    val Combination = ListBuffer[String]()
    if(Shift) Combination += "Shift"
    if(Control) Combination += "Ctrl"
    if(Alt) Combination += "Alt"
    Combination.mkString(" + ")
  }
}

class SimpleBinding(val SpecKeys:SpecKey,val Key:Char) {
  override def toString: String = SpecKeys.toString + " + " + Key
}

object SimpleBinding {
  def apply(SpecKeys:SpecKey, Key: Char): SimpleBinding = new SimpleBinding(SpecKeys, Key)

  def apply(Combination:String): SimpleBinding = {
    val SpecKey = new SpecKey()
    var LastKey = ""
    val SplitData = Combination.split("[+]").map(_.trim)
    if(SplitData.length >= 2 && SplitData.length < 5){
      SplitData.foreach(key => {
        key.toUpperCase() match {
          case "CTRL" => SpecKey.Control = true
          case "ALT" => SpecKey.Alt = true
          case "SHIFT" => SpecKey.Shift = true
          case x => if(key.length == 1 && key.head.isUpper && key == SplitData.last) LastKey = x
          else throw new IllegalArgumentException("Последняя клавиша должна быть заглавная буква латинского алфавита!")
        }
      })
        if(LastKey.isEmpty) throw new IllegalArgumentException("Неверный набор клавиш: в комбинации должна быть ровно одна латинская клавиша!")
      new SimpleBinding(SpecKey, LastKey.head)
    } else throw new IllegalArgumentException("Неверный набор клавиш: в комбинации должно быть от 2 до 4 клавиши!")

  }
}
