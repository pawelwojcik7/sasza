package xd



object Cwiczenia extends App{

  val liczba = "3435445"

  private val wynik = Cwiczenia1.stringToLong(liczba)


  println(wynik)
}


object Cwiczenia1 {

  def stringToLong(liczba: String): Long = { // liczba = "123456789"
    val charList: List[Char] = liczba.toList // List('1','2','3','4','5','6','7','8','9')

    if(charList.isEmpty) throw new RuntimeException("Empty parameter")

    charList.map(charToInt) // List(1,2,3,4,5,6,7,8,9)
      .reverse // List(9,8,7,6,5,4,3,2,1)
      .zipWithIndex // zipWithIndex robi tupla z wartoscia i indeksem = List((9 -> 0), (8 -> 1) itd)
      .map(calculate)
      .sum // dodajemy kazde wyniki, to samo co foldLeft(0)((firs, second) => first + second) - fold z listy czegos robic cos typu czegos Lista[Int] => Int, 0 to default, a potem jest funkcja która zamienia tupla2 kolejnych elementów w poejdynczy element


      //list(1,2,3,4) . foldLeft(0)((first, second) => first + second)

      // jak lista pusta to zwroci 0

      //jak wyglada wykonanie (first, second) => first + second
      //1 obrót 0 + 1 = 1     - (default, element1) -> wynik1
      //2 obrót  1 + 2 =3     - (wynik1, element2) -> wynik2
      //3 obrot  3 + 4 = 7    - (wynik2, element3) -> wynik3


  }

  private def calculate(tuple: (Int, Int)): Long = {  //(7, 2)
    val potega = tuple._2 //2
    val liczba = tuple._1 //7

    liczba * potega10(potega) // malo optymalne to jest bo dla kazdej liczby jest rekurencyjnie przeliczana potega dla potegi 4 musi przeliczyc 3, 2 i 1 potege i tak samo dla 5 musi 4,3,2,1 wiec sie powtarza
  }

  def potega10(potega: Int): Long = {
    potega match
      case 0 => 1
      case other => 10 * potega10(potega - 1)   //  rekurencja
  }


  private def charToInt(character: Char): Int = {
    character match
      case '0' => 0
      case '1' => 1
      case '2' => 2
      case '3' => 3
      case '4' => 4
      case '5' => 5
      case '6' => 6
      case '7' => 7
      case '8' => 8
      case '9' => 9
      case other => throw new RuntimeException(s"Unexpected character: $other")
  }


}
