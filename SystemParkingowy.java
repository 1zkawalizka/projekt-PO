import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Scanner;

        public class SystemParkingowy {
            private List<Pojazd> pojazdy; // DS
            private LocalDateTime aktualnyCzas; // DS

            private List<RejestrParkowania> historia; // PC
            private int pojemność; // PC
            private double opłataZaGodzine; // PC

            public SystemParkingowy(int pojemność, double opłataZaGodzine) {
                this.pojazdy = new ArrayList<>(); // DS
                this.aktualnyCzas = LocalDateTime.of(2025, 1, 1, 0, 0, 0); // DS

                this.historia = new ArrayList<>(); // PC
                this.pojemność = pojemność; // PC
                this.opłataZaGodzine = opłataZaGodzine; // PC
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

            // PAULINA CHOJNOWSKA POCZATEK KODU
            public boolean sprawdzDostepnosc() {
                return pojazdy.size() < pojemność;
            }

            public List<Pojazd> pobierzAktualnePojazdy() {
                return new ArrayList<>(pojazdy);
            }

            public String generujRaportDzienny() {
                String raport = "";

                raport += "Raport dzienny\n";
                raport += "Data: " + aktualnyCzas.toLocalDate() + "\n";

                raport += "Zajęte miejsca: " + pojazdy.size() + "\n";
                raport += "Wolne miejsca: " + (pojemność - pojazdy.size()) + "\n\n";

                raport += "Zaparkowane pojazdy:\n";
                for (Pojazd p : pojazdy) {
                    raport += "- " + p.getNumerRejestracyjny() + " " + p.getMarka() + " " + p.getModel() + "\n";
                }
                return raport;
            }

            public void wyswietlHistorie() {
                if (historia.isEmpty()) {
                    System.out.println("Brak historii parkowania.");
                    return;
                }

                System.out.println("Historia parkowania:");
                for (RejestrParkowania rejestr : historia) {
                    System.out.println(
                            rejestr.getPojazd().getNumerRejestracyjny() +
                                    " - Wjazd: " + rejestr.getCzasWjazdu() +
                                    ", Wyjazd: " + rejestr.getCzasWyjazdu() +
                                    ", Opłata: " + rejestr. getOplata() + " zł"
                    );
                }
            }
            // PAULINA CHOJNOWSKA KONIEC KODU

    public static void main(String[] args) {
        SystemParkingowy systemParkingowy = new SystemParkingowy(50, 10.0); // Dodane parametry
        Scanner scanner = new Scanner(System.in);
        int wybor;
        do {
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
            switch (wybor) {
                case 1:
                    systemParkingowy.zarejestrujWjazd();
                    break;
                case 2:
                    System.out.println("Podaj Numer Rejestracyjny: ");
                    String numerRejestracyjny = scanner.next();
                    systemParkingowy.zarejestrujWyjazd(numerRejestracyjny);
                    break;
                case 3:
                    System.out.println("Dostępne miejsca: " +
                            (systemParkingowy.sprawdzDostepnosc() ? "TAK" : "NIE"));
                    break;
                case 4:
                    System.out.println("Aktualne pojazdy:");
                    for (Pojazd p : systemParkingowy.pobierzAktualnePojazdy()) {
                        System.out.println("- " + p.getNumerRejestracyjny());
                    }
                    break;
                case 5:
                    System.out.println(systemParkingowy.generujRaportDzienny());
                    break;
                case 6:
                    systemParkingowy.przesunCzas();
                    break;
                case 7:
                    System.out.println("Historia pojazdów:");
                    systemParkingowy.wyswietlHistorie();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Podano nieprawidlowy wybor");
                    break;
            }
        } while (wybor != 0);

        scanner.close();
    }
}