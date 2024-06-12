import java.time.{OffsetDateTime, OffsetTime}

object Polimorfizm {

  lazy val time: OffsetDateTime = OffsetDateTime.now() // lazy val to wartosc która jest inicjalizowana w momencie wywołania, czyli kiedy wołamy Polimorfizm.time

  val time2: OffsetDateTime = OffsetDateTime.now() // ta wartośc zostanie zainicjalizowana w momencie odpalenia programu, i nie zmieni się do końca jego trwania


  val opis = "Polimorfiz to dziedziczenie, czyli to że Cat extends Animal to polimorfizm"


}


trait Animal{

  def name: String // metoda abstrakcyjna którą trzeba zaimplementować

  def makeSound: String  = "" // metoda defultowa, nie trzeba jej implementować ale można ją napdisać

}

case object Cat extends Animal {
  override def name: String = "cat"

  override def makeSound: String = "miau" // nadpisanie metody defaultowej
}

case class Dog(name: String) extends Animal // defy i vale mozna overridowac w obie strony, def mozna overridowac valem, i val defem, name to tak naprawde val


class Snake extends Animal {
  override val name: String = "snake" // def zoverridowany valem
}

object Turtle extends Animal{
  override def name: String = "turtle"
}

sealed abstract class Machine(val name: String) // sealed mówi o tym że wszystkie implementacje musza znaleźc sie w tym samym pliku, nie ma mozliwosci utworzenia implementacji poza tym plikiem, można stosowac też sealed traity

case object Tractor extends Machine("tractor")

object Harvester extends Machine("harvester")

case class Tractor(producent: String) extends Machine(producent)


object ex {

  val machine: Machine = Tractor("fendt")

  val res = machine match // gdy napisze sie ma to ide podpowiada match (exchaustive) -> odrazu automat wytpisze wszystkie cassy
    case Tractor => ???
    case Harvester => ???   // ryzykowne troche bo to zwykły object !!!! , lepiej do matchy gdzie stosowane jest słow kluczowe case uzywac case class i case objectów
    case Tractor(producent) => ???
    case XD => ???
    // nie ma case _ lub case other dlatego że sealed gwarantuje nam że nie ma nigdzie żadnej innej implementacji w plikach której sie nie spodziewamy

  case object XD extends Machine("bulbulator") with Animal // nie każe nadpisywac metody name bo Machine ma takie pole które jest z defoultu przykryte

  // traity i abstract classy

  // róznice - jedna rzecz moze extendowac tylko jedna abstract classe ale wiele traitów, mozna to łaczyc, dalsze dziedziczenie daje sie przez słow with

  //trait nie powinien miec val a same def, w odpowiednich implementacjach mozna to przykryc valem jesli potrzeba


}
