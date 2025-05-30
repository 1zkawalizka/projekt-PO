import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Scanner;

public class SystemParkingowy {
    private List<Pojazd> pojazdy;
    private LocalDateTime aktualnyCzas;

    public SystemParkingowy() {
        // Damian Sadowski - Poczatek kodu
        this.pojazdy = new ArrayList<>();
        this.aktualnyCzas = LocalDateTime.of(2025, 1, 1, 0, 0, 0); // LocalDateTime.of(rok, miesiac, dzien, godzina, minuta, sekunda)
        // Damian Sadowski - Koniec kodu
    }

    // Damian Sadowski - Poczatek kodu
    public void zarejestrujWjazd(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Typ pojazdu \n 1. Samochod osobowy \n 2. Ciezarowka \n 3. Motocykl");
        int typPojazdu = scanner.nextInt();

        System.out.println("Numer Rejestracyjny Pojazdu: ");
        String nrRejestracyjny = scanner.next();

        System.out.println("Marka pojazdu: ");
        String markaPojazdu = scanner.next();

        System.out.println("Model pojazdu: ");
        String modelPojazdu = scanner.next();

        switch(typPojazdu){
            case 1:
                System.out.println("Czy posiada LPG? true/false");
                boolean czyMaLPG = scanner.nextBoolean();

                System.out.println("Ilu osobowy? ");
                int iluOsobowy = scanner.nextInt();

                Pojazd pojazd = new Samochod(nrRejestracyjny, markaPojazdu, modelPojazdu, aktualnyCzas, czyMaLPG, iluOsobowy);
                pojazdy.add(pojazd);
                break;
            case 2:
                System.out.println("Jaka wysokosc w cm? ");
                double wysokosc = scanner.nextDouble();

                System.out.println("Jaka ladownosc? ");
                double ladownosc = scanner.nextDouble();

                pojazd = new Ciezarowka(nrRejestracyjny, markaPojazdu, modelPojazdu, aktualnyCzas, wysokosc, ladownosc);
                pojazdy.add(pojazd);
                break;
            case 3:
                System.out.println("Pojemnosc silnika: ");
                int pojemnoscSilnika = scanner.nextInt();

                System.out.println("Typ? cross/sportowy/scigacz");
                String typ = scanner.next();

                pojazd = new Motocykl(nrRejestracyjny,markaPojazdu, modelPojazdu, aktualnyCzas, pojemnoscSilnika, typ);
                pojazdy.add(pojazd);
                break;
        }
    }

    public double zarejestrujWyjazd(String nrRejestracyjny){
        for (Pojazd pojazd : pojazdy) {
            if (pojazd.getNumerRejestracyjny().equals(nrRejestracyjny)) {
                pojazd.wyjazdCzas(aktualnyCzas);
                double oplata = pojazd.getOplata();
                pojazdy.remove(pojazd);
                return oplata;
            }
        }
        return 0.0;
    }

    public void przesunCzas(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Jaka jednostka czasu? \n 1. Rok \n 2. Miesiac \n 3. Dzien \n 4. Godzina \n 5. Minuta \n 6. Sekunda \n\n");
        int wyborJednostkiCzasu = scanner.nextInt();
        System.out.printf("O ile jednostek? ");
        int oIleJednostek = scanner.nextInt();
        switch (wyborJednostkiCzasu){
            case 1:
                System.out.println(aktualnyCzas + " -> + " + oIleJednostek + " Lat -> " + aktualnyCzas.plusYears(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusYears(oIleJednostek);
                break;
            case 2:
                System.out.println(aktualnyCzas + " -> + " + oIleJednostek + " Miesiecy -> " + aktualnyCzas.plusMonths(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusMonths(oIleJednostek);
                break;
            case 3:
                System.out.println(aktualnyCzas + " -> + " + oIleJednostek + " Dni-> " + aktualnyCzas.plusDays(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusDays(oIleJednostek);
                break;
            case 4:
                System.out.println(aktualnyCzas + " -> + " + oIleJednostek + " Godzin -> " + aktualnyCzas.plusHours(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusHours(oIleJednostek);
                break;
            case 5:
                System.out.println(aktualnyCzas + " -> + " + oIleJednostek + " Minut -> " + aktualnyCzas.plusMinutes(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusMinutes(oIleJednostek);
                break;
            case 6:
                System.out.println(aktualnyCzas + " -> + " + oIleJednostek + " Sekund -> " + aktualnyCzas.plusSeconds(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusSeconds(oIleJednostek);
                break;
            default:
                System.out.printf("Podano nieprawidlowo cos");
        }
    }
    // Damian Sadowski - Koniec kodu


    public static void main(String[] args) {

        // Damian Sadowski - Poczatek Kodu
        SystemParkingowy systemParkingowy = new SystemParkingowy();
        Scanner scanner = new Scanner(System.in);
        int wybor;
        do{
            System.out.println("Co chcesz zrobic? \n " +
                    "1. Wjazd \n " +
                    "2. Wyjazd \n " +
                    "3. Sprawdz Dostepnosc \n " +
                    "4. Lista aktualnych pojazdow \n " +
                    "5. Generuj raport dzienny \n " +
                    "6. Przesun czas symulacji \n " +
                    "7. Historia pojazdow \n " +
                    "0. Wyjdz");
            wybor = scanner.nextInt();
            switch (wybor){
                case 1:
                    systemParkingowy.zarejestrujWjazd();
                    break;
                case 2:
                    System.out.println("Podaj Numer Rejestracyjny: ");
                    String numerRejestracyjny = scanner.next();
                    systemParkingowy.zarejestrujWyjazd(numerRejestracyjny);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    systemParkingowy.przesunCzas();
                    break;
                case 7:
                    break;
                case 0:
                    break;
                default:
                    System.out.printf("Podano nieprawidlowo cos");
                    break;
            }
        }while(wybor != 0);
        // Damian Sadowski - Koniec Kodu

        System.out.println("Lista samochodów na parkingu:");
        for (Pojazd pojazd : systemParkingowy.pojazdy) {
            if (pojazd instanceof Samochod) {
                System.out.println("- " + pojazd.getNumerRejestracyjny() + " | " + pojazd.getMarka() + " " + pojazd.getModel());
            }
        }

        scanner.close();
    }
}