package xd



@main // specjalne onzaczenie metody uruchomieniowej
def main(): Unit = { // w scali 3 metoda może być top level, to znaczy że nie musi istniec w klasie ani w obiekcie
  println("Hello world from top level method")
}

class Start7{
  def main(args: Array[String]): Unit = { // metoda uruchomieniowa, nie da sie uruchomic bo trzeba by stworzyc instancje Start7
    println("Hello world from class")
  }
}

case class Start2(){
  def main(args: Array[String]): Unit = { // metoda uruchomieniowa, ale nie da sie uruchomic bo nie ma nigdzie instancji Start2
    println("Hello world from case class")
  }
}


trait Start4{

  def main(args: Array[String]): Unit = { // metoda uruchomieniowa // w traicie tez sie nie wykona
    println("Hello world from trait impl")
  }

}

object Impl extends Start4 // to da sie uruchomic bo jest implementacja traitu


object Start extends App{ // extends App -> wszystko co jest w obiekcie sie wykona
  println("Hello world from object")
}

case object Start1 extends App{// extends App -> wszystko co jest w case obiekcie sie wykona
  println("Hello world from case object")
}