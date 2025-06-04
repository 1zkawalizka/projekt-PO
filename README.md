# System Parkingowy – Projekt Java

## Opis

Projekt przedstawia system parkingowy napisany w języku Java, który umożliwia:

- rejestrację wjazdów i wyjazdów pojazdów,
- kontrolę dostępnych miejsc,
- przesuwanie czasu symulacji,
- generowanie dziennego raportu,
- przegląd historii parkowania.

W projekcie zaimplementowano trzy typy pojazdów:

- Samochód osobowy
- Ciężarówka
- Motocykl

---

## Uruchomienie programu
Program uruchamia się z poziomu metody main() w klasie SystemParkingowy. Po uruchomieniu użytkownik może wybrać jedną z opcji:

1. Wjazd 
   – Umożliwia wprowadzenie danych nowego pojazdu (typ, numer rejestracyjny, marka, model i dane szczegółowe).  
   – Sprawdza dostępność miejsc oraz unikalność numeru rejestracyjnego.  
   – Tworzy obiekt pojazdu odpowiedniego typu i dodaje go na parking.

2. Wyjazd
   – Użytkownik podaje numer rejestracyjny pojazdu.  
   – System sprawdza, czy pojazd znajduje się na parkingu.  
   – Oblicza opłatę na podstawie czasu postoju i typu pojazdu, usuwa pojazd z parkingu i zapisuje historię.

3. Sprawdź dostępność 
   – Sprawdza, czy na parkingu są jeszcze wolne miejsca.  
   – Wyświetla informację: „TAK” jeśli dostępne, „NIE” w przeciwnym przypadku.

4. Lista aktualnych pojazdów
   – Wyświetla listę wszystkich pojazdów aktualnie znajdujących się na parkingu.  
   – Pokazuje typ pojazdu, numer rejestracyjny, markę, model oraz czas wjazdu.

5. Generuj raport dzienny 
   – Generuje raport tekstowy zawierający datę, liczbę zajętych i wolnych miejsc oraz listę aktualnie zaparkowanych pojazdów.

6. Przesuń czas symulacji 
   – Pozwala symulacyjnie przesunąć aktualny czas systemowy o wybraną jednostkę (lata, miesiące, dni, godziny, minuty, sekundy).  
   – Czas ten wpływa później na naliczanie opłat przy wyjeździe pojazdu.

7. Historia pojazdów
   – Wyświetla pełną historię wjazdów i wyjazdów pojazdów.  
   – Dla każdego pojazdu pokazuje: typ, numer rejestracyjny, markę, model, czas wjazdu i wyjazdu (jeśli był), oraz opłatę.

0. Wyjdź
   – Zamyka program.


## Struktura klas

- **Pojazd** *(abstrakcyjna)* – klasa bazowa zawierająca dane pojazdu i metody do obliczania opłat.
- **Samochod**, **Ciezarowka**, **Motocykl** – klasy dziedziczące po **Pojazd**, zawierające specyficzne cechy i zasady naliczania opłat.
- **RejestrParkowania** – klasa przechowująca dane o czasie wjazdu, wyjazdu i naliczonej opłacie dla danego pojazdu.
- **SystemParkingowy** – główna klasa aplikacji odpowiedzialna za logikę, obsługę danych i komunikację z użytkownikiem przez konsolę.

---

## Przykładowe funkcje programu

- Obsługa wielu typów pojazdów i ich parametrów.
- Walidacja danych wejściowych i obsługa wyjątków.
- Przesuwanie czasu w symulacji.
- Raport stanu parkingu.
- Historia zaparkowanych i pojazdow ktore wyjechaly.

---

## Autorzy

- **Damian Sadowski**, **Paulina Chojnowska**, **Izabela Szorc** 

