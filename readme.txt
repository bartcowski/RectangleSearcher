Bartosz Ciućkowski
nr indeksu: 283719

AAL - punkty symetryczne
Problem polega na znalezieniu prostokąta o jak najmniejszym obwodzie, który zawierać będzie każdy z N początkowych punktów
lub (jeśli zmniejszy to obwód) jego odbicie względem prostej y=x, tzn. każdy punkt z pary (oryginalny, odbity).
Boki prostokąta są równoległe do osi, a współrzędne są liczbami całkowitymi.

W programie dostępne są 3 metody uruchomienia:
1. do wczytywania współrzędnych punktów z pliku
2. do generowania dowolnej ilości punktów z losowymi współrzędnymi
3. do wykonywania testów czasu wykonania
Wzory wszystkich metod:
java Main m1 (nazwaPliku).txt
          m2 (liczbaPkt) (współrzędneOD) (współrzędneDO)
          m3 (n) (krok) (k) (powtórz)
              gdzie: n - liczba punktów w początkowej kolekcji (np. 1000)
                     krok - o ile kolekcja powinna się powiększyć przy każdym 'k' (np. 500)
                     k - ile razy o wartość 'krok' powinna powiększać się kolekcja (np. 30)
                     repeat - ile razy powtórzyć test dla tej samej liczby punktów (np. 10)
Wszystkie argumenty (poza nazwą pliku oraz współrzędneOD, który może być ujemne) powinny być liczbami naturalnymi, dodatkowo:
liczbaPkt > 5, współrzędneOD < współrzędneDO.

Metoda polega na początkowym znalezieniu prostokąta w kolekcji punktów oryginalnych (bez odbić).
Następnie sprawdzenie czy nie znajduje się on w jednej z trzech pozycji, w których punkty odbite nie mają wpływu na zmniejszenie obwodu, tj:
1. cała kolekcja POD y=x
2. cała kolekcja NAD y=x
3. istnieją dwa punkty o współrzędnych (MaxX, MaxY) oraz (MinX, MinY), znajdują się one na prostej y=x i rozpinają cały prostokąt
Jeśli żadna z nich nie zaszła to: sprawdzane są wszystkie krawędzie obecnego prostokąta, ta której odbicie spowoduje największe pomniejszenie
obwodu, zostaje odbita (do kolekcji dodawane są odbicia i to one będą uwzględniane w dalszych rozważaniach, a punkty oryginalne będą pomijane).
Następnie cykl jest powtarzany, aż do momentu kiedy żadne ulepszenie nie zostanie znalezione.

Zastosowane algorytmy to dostarczone przez Java 8 sortowanie z interfejsu 'List', które jest zmodyfikowaną wersją
Merge Sort i gwarantuje złożoność O(n logn).
Struktury danych to ArrayList - przechowuje kolekcje punktów.

Pliki źródłowe (*.java):
Main - plik główny, decyduje o użyciu odpowiedniego trybu użycia na podstawie dostarczonych argumentów
RectangleSearcher - zawiera klasę wykonującą szukanie prostokąta
Point - zawiera klasę reprezentującą pojedynczy punkt
PointsCollection - zawiera klasę reprezentującą kolekcję punktów, wraz z możliwymi operacjami na niej
FoundRectangle - zawiera klasę reprezentującą znaleziony prostokąt
RandomGenerator - zawiera klasę, która losuje liczby całkowite wykorzystywane przy tworzeniu punktów
MyBenchmark - zawiera klasę odpowiedzialną za przeprowadzanie testów w 3. trybie wykonania
Result Display - zawiera klasę odpowiedzialną za wypisywanie użytkownikowi informacji o wynikach
HelpInfo - zawiera klasę pomocniczą, która wypisuje instrukcje użycia w przypadku np. podania przez użytkownika złych argumentów
Dodatkowo: coordinatesX.txt to przykładowe pliki ze współrzędnymi do wczytywania przez program

Informacje o przyjętych założeniach i praktykach:
1. ograniczenie wielkości losowanych liczb całkowitych w 3. trybie wykonania, kiedy nie zostaje ono podane przez użytkownika
2. losowane punkty mają mniejsze prawdopodobieństwo pojawienia się blisko ustalonych granic
3. przy podaniu złego argumentu program wyświetla informację o poprawnym użyciu i kończy działanie
