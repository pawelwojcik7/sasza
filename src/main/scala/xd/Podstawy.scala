package xd

case class Podstawy(
    cokolwiek: String // coolwiek to wartośc którą podstawy przyjmują w konstruktorze
                   ) {

  def opis(): String = "case class to klasa którą tworzymy bez słowa new, najczescie reprezentuje jakiś serwis albo zbiór cech pewnej rzeczy"

  def opisBezNawiasów: String = "kontrukcja metody nie musi miec () jezeli nie przyjmuje parametrów, def to słowo kluczowe dla metody, potem nazwa, : TYP ZWRACANY  = ciało"

  def opisCd(): String = {
    val rezultat = "ciało metody może byc bez {} jezeli jest jednolinijkowa lub zawierac sie w {}"


    rezultat // 2 ukosniki to komentarz w kodzie, ostatnia wartość w metodzie to wartość zwracana przez metode
  }

  def opisss(): Unit = {
    val napis = "unit to metoda która nic nie zwraca, ona cos robi i nie zwraca rezultatów"
    println(napis) // println drukuje na konsole jakiś napis, w zasadzie może wydrukowac cokolwiek , zwraca unit ale nie jest ostatnia metoda w tej metodzie

    opis() // wywowłanie metody napis, gdyby nie było nizej nawiasu, to metoda opisss() zwracała by to samo co metoda opis() czyli string
    () // () to unit dosłownie, jeżeli np na końcu metody wywołujemy coś co coś zwraca np. opisss() ktróy zwraca string to musimy na koncu zastosowac taki nawias zeby oznaczyc że to co jest wyżej to nie jest zwracane przez te metode

  }


  def dodaj(a: Int, b: Int): Int = a + b // metoda z dwoma parametrami
  
  def dodaj2(a: Int)(b: Int): Int = a + b // metoda z dwoma parametrami ale z dwoma nawiasami, wywołuje się : dodaj2(1)(2)  -- doda 1 do 2 - moze byc wiecej nawiasów, 2 nawiasów uzywa sie zazwyczaj do specjalnych parametrów lub jeżeli chcemy przekazywac czesciową funkcje dalej 

  val fn: Int => Int = dodaj2(5) // uzupelniajac czesciowo funkcje uzyskujemy vala który jest funkcja przekształcającą Inta w(=>) Inta
  
  def fn1(x: Int): Int => Int = dodaj2(x) // również funkcja moze zwracac funkcje
  
  def fZlozona(x: Int, f: Int => Int): Int = f.apply(x) // funkcja przyjmuje ionna funkcje jako parametr, wywołuje sie ją przez .apply
  
  fZlozona(10, fn) // wywołanie funkcji zlozonej, to to samo co dodaj(5)(10), kaze wywołanie funkcji w ciele klasy dzieje się podczas jej tworzenia, wiec kiedy stworzymy Podstawy(cos) to ta linia sie wykona
  fZlozona(11, fn1(22)) // tez sie wykona
  
  def dodaj3(a: Int, b: Int = 0) = a+ b // to metoda z parametrem defulowym, mozna wywołać: dodaj3(1,2) - zwróci 3 albo dodaj3(1) - zwróci 1 bo parametr b jest defaultowy

}

case object Podstawy{

  def opis(): String = "case object to obiekt towarzyszący do case classy najczesciej, w nim znajdują sie metody statyczne, wywołuje się : Podstawy.opis(), powinien sie nazywac tak samo jak case class której towarzyszy"

  def opis2(): String = "moze tez towarzyszyc traitowi, abstract classie - tez powinien nazywac sie tak samo najlepiej"
  
  
  def specjalnaMetodaKopiujaca(pd: Podstawy): Podstawy = pd.copy("inne cokolwiek") // metoda kopy zwraca nowy obiekt ze zmienionym parametrem, jezeli case class ma wiecej parametrów w konstruktorze niz 1 to można np podmienic jeden z nich a reszte skopiowac z orginalnego obiektu, scala jest niemutowalna wiec nie zmienia to wartosci na oryginalnym obiekcie ale tworzy nowy, copy jest dostepne na każdej case classie
}


object Podstawy2{

  def opis: String = "object to praktycznie to samo co case object, nie implmentuje tylko pod spodem metod jak equals() i hashCode(), obiektów nie powinno sie porównywac ze sobą Object1 == Object2 w przeciwieństwie do case objectów"

}

class KlasaPodstawy(val napis: String){

  val opis: String = "val to wartosc której nie da się zmienić, class w przeciwienstwie do case class tworzy się słowem kluczowym new np. new KlasaPodstawy(tu trzeba wstawic stringa)"

}

