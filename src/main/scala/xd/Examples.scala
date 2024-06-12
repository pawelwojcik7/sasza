package xd

import scala.util.Try

object Examples {

  // scala jest niemutowalna, tzn że raz zainicjalizowana wartość sie nie zmieni, nie dodajemy elementów do istniejacej listy, tylko tworzymy nowa liste z tamtej z dodanymi elemntami
  
  
  
  val ex1 = "napis" // vala nie da sie zmienic    ex1 = 10 nie zadziała !!! - uzywamy głownie vali i staramy sie uzywac zawsze vali 
  
  // var - wartosc da sie zmienic
  
  var xd = 10
  
  println(xd) // wydrukuje 10
  
  xd = 11
  
  println(xd) // wydrukuje 11
  
  
  //pattern matching

  val resultat: String = SimpleTypesExamples.opcjonalnyNapis match // opcje można matchowac do Soma i Nona, to taka extrakcja wartosci w locie
    case Some(value) => value
    case None => "cos innego"
   
  val resultatInaczej1: String = SimpleTypesExamples.opcjonalnyNapis.getOrElse("cos innego")
    

  SimpleTypesExamples.lista match
    case head :: Nil => "porownanie do listy z jednym elementem, head to 1 element, Nil to ogon który jest zmatchowany do pustej listy - lista jednoelementowa"
    case head :: tail => "porównanie do listy z pierwszym elementem i tailem, tail można dalej patternamtchować w ten sam sposób - lista conajmniej 2 elementowa"
      tail match
        case head :: Nil => "jw"
        case head :: tail => "jw"
        case Nil => "jn"
    case Nil => "porownanie do pustej listy - pusta lista"

  // praktyczny przykład z rekurencja


  def znajdzOstatniElement[A](lista: List[A]): Option[A] = { // tutaj lista z generykiem, A może być czym kolwiek tak naprawde, wywołanie z listą stringów zwróci opcje od stringa, mozna czytac jako lista czegokolwiek
    lista match
      case head :: Nil => Some(head) // lista 1 elementowa, zwracamy opcje od head
      case _ :: tail => znajdzOstatniElement(tail) // _ to nazwanie parametru który nas nie interesuje, mozna to nazwac head, ale w dalszym wykonaniu nie bierze udziału wiec nie trzeba go nazywać, pierwszy element porzucamy, szukamy ostatniego elementu w ogonie
      case Nil => None // pusta lista, zwracamy pustą opcje czyli None

  }


  val ostatniElementOpcja: Option[String] = znajdzOstatniElement(SimpleTypesExamples.lista) // wstawilismy liste stringów, otrzymujemy opcje od stringa

  val ostatniElementInaczej = SimpleTypesExamples.lista.reverse.headOption // .reverse - odwracamy liste, .headOption - zwraca pierwszy element jako opcje odwróconej listy,

  val ostatniElementLadnie = SimpleTypesExamples.lista.lastOption // ostatni opcja

  // przemapowania

//zróbmy z listy intów liste stringów


  private val listaIntów: List[Int] = 1 :: 2 :: 3 :: 4 :: Nil // to samo co List(1,2,3,4) - :: to łaczenie elementu z listą, czyta sie od tyłu, Nil to lista, łaczymy ja z 4, potem taka liste łaczymy z 3 itd

  val listaStringów: List[String] = listaIntów.map(e => e.toString)

  val listaIntówŁadnie = listaIntów.map(_.toString) // jak wołamy metode na tym parametrze możemy zastosowac _, nie musimy inta nazywac jako e

  val listaIntowZCzymsPoDrodze: List[String] = listaIntów.map{ // uzywamy {}
    liczba =>

      println(liczba) // po drodze mapowania drukujemy

      liczba.toString // i na końcu zwracamy stringa
  }

  // zróbmy z listy stringów liste intów o ile sie da

  val ls = List("1", "2", "tego sie nie uda")

  val listaOpcji: List[Option[Int]] = ls.map{
    napis =>
      Try{
       napis.toInt // w try wywołujemy niebezpieczna funkcje, z inta zawsze da sie zrobic stringa, ale nie z kazdego stringa da sie zrobic inta
      }.toOption // jezeli uda sie zrobic to co w try dostaniemy some od wyniku, inaczej none

  }


  val odfiltorwana: List[Int] = listaOpcji.filter(_.isDefined).map(_.get) // isDefined to metoda na opcji, jezeli sprawdzimy czy jest defined mozemy zrobic get, jezeli zrobimy get na nonie poleci blad dlatego odfiltrowujemy 

  val odfiltorowanaŁadnie: List[Int] = listaOpcji.collect{case Some(value) => value} // kolekcjonujemy tylko te które sie matchują do Some() czyli maja wartosc

  //funkcja flatMap wystepuje na kolekcjach, lista to kolekcja wieloelementowa, opcja to kolekcja jedno elementowa

  // flatMap na opcji w srodku musi przyjac funckje która z z wartosci kolekcji robi ta sama kolekcjie czyli opcje

  val listaList: List[List[Int]] = listaOpcji.map(_.toList) // toList na opcji zwraca Nil jezeli to None, albo liste jedno elementową jezeli jest Some

  val splaszczona: List[Int] = listaList.flatten // polaczylismy liste list w liste, to to samo co oflitrowana i odfiltrowana ladnie

  val splaszczonaLacznie = listaOpcji.map(_.toList).flatten // podpowiada żeby uzyc flatmap

  //inaczej

  val splaszczona2: List[Int] = listaOpcji.flatMap(_.toList) // to to samo co odflitrowana, i odfiltrowana łądnie,

  def rzucablad(x: Int): Int = {
    if(x ==2) throw new RuntimeException("") else x
  }

  val tryVal: Try[Int] = Try{rzucablad(2)}

  val resultatTry: Either[Throwable, Int] = tryVal.toEither // either to taka para, z lewej jest blad a z prawej wartośc jezeli sie udało, ale tylko jedna strona jest zawsze zdefiniowana

  resultatTry match
    case Left(error) => println(error.getMessage) // either matchujemy do Left(value) - value to throwable czyli klasa bazowa kazdego bledu - polimorfizm - RuntimeException extends Exception, a Exception extends Throwable
    case Right(value) => println(value) // right to wartosc poprawna

  resultatTry match
    case Left(error) => error match
      case rt: RuntimeException => "teraz mamy pewnosc że error to RuntimeException"
      case _ => "_ to cokolwiek innego niz RuntimeException, jezeli nam to tu nie potrzebne, czyli tak naprawda kazde inne throwable"
    case Right(value) => value.toString


    val ex: String =   resultatTry match
        case left @ Left(error) => "mozemy nazwac parametr przez małpe, wtedy w tym scope mamy left które = Left[Throwable], mamy tez dostepne error"
        case Right(value) => value.toString


  val opcjaResultatow: Option[Int] = resultatTry.toOption // jezeli Left(_) to zwraca None, jak Right(value) to zwraca Some(value), _ czytaj jako cokolwiek

  val resultatLubDefault: Int = opcjaResultatow.getOrElse(0) // jezeli Some(value) to value, jeżeli none to 0

  // tuple i mapy

  val exampleTuple: (Int, Int) = (1, 2)

  val pierwszyElementExampleTupla = exampleTuple._1

  val drugieElementExampleTupla = exampleTuple._2

  val exampleTuple3: (Int, String, Option[Char]) = (1, "napis", Some('a')) // tuple moge byc bardzo długie, chyba nawet 22 elementy, ale to bez sensu, wtedy lepiej zrobic case class która przechowuje 22 elementy

  val tupleInaczej: (Int, Int) = 1 -> 2 // -> to to same co (1,2) - taki konstruktor tupla


  val listaTupli: List[(Int, Int)] = exampleTuple :: tupleInaczej :: Nil

  val mapa: Map[Int, Int] = listaTupli.toMap // liste tupli z 2 wartosciami da sie zaminic w mape, pierwszy element to klucz drugi element tupla to wartosc




}


object SimpleTypesExamples{

  val napis: String = "napis"

  val liczba: Int = 1

  val liczbaZmiennoprzecinkowa: Float = 2.1

  val dlugaLiczba: Long = {
    100000000000L
  }

  val znak: Char = 'a'

  val lista: List[String] = List("element1", napis, "elemtnt2")

  val pustaListaNapisów: List[String] = List()

  val pustaListaNapisów2: List[String] = Nil // Nil to pusta lista czegokolwiek, nie zaleznie czy Stringów czy czegokololwiek w zasadzie

  val opcjonalnyNapis: Option[String] = Option("napis") // konstruktor Option uzywamy kiedy nie jestesmy pewni czy wartosc jaka tam wstawiamy nie jest nullem, tutaj wiemy ze mamy napis wiec raczej stosuje sie some

  val opcjonalnyNapis2: Option[String] = Some("napis") // Some to kontruktor opcji, uzywamy wtedy gdy mamy pewnosc że wstawiana wartość nie jest nullem

  val opcjonalnyNapis3: Option[String] = None // None to jak troche Nil tylko do opcji, reprezentuje pustą opcje

  def interpolacjaStringa(napis: String): String = s" interpolacja działa przez literke s oraz cudzysłów, dzieki temu mozemu przez dolar wstawiac tu wartosc : $napis"

  def roznice(): String = "val od def - roznica jest taka ze def jest inicjalizowany w momencie wykonania, a val jest inicjalizowany raz i jest zawsze taki sam"

}