import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
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
                try {
                    if(sprawdzDostepnosc()){
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("Typ pojazdu: \n 1. Samochod osobowy | 2. Ciezarowka | 3. Motocykl : ");
                        int typPojazdu = scanner.nextInt();
                        if(!(typPojazdu == 1 || typPojazdu == 2 || typPojazdu == 3)){
                            throw new PodanoNiepoprawneDaneLubPusty("ERROR: wybrano niewlasciwy typ pojazdu, wybierz pojazd z zakresu 1-3. \n");
                        }
                        scanner.nextLine(); // usuwamy z buforu /n

                        System.out.print("Numer Rejestracyjny Pojazdu: ");
                        String nrRejestracyjny = scanner.nextLine();
                        if(nrRejestracyjny.length() < 2){
                            throw new PodanoNiepoprawneDaneLubPusty("ERROR: podano niepoprawny numer rejestarcyjny (zbyt krotki) \n");
                        }
                        for(Pojazd pojazd : pojazdy){
                            if(pojazd.getNumerRejestracyjny().equals(nrRejestracyjny)){
                                throw new TakaRejestracjaJestJuzNaParkingu("ERROR: Samochod o takim numerze rejestracyjnym znajduje sie juz na parkingu, prosimy o zawolwanie obslugi. \n");
                            }
                        }

                        System.out.print("Marka pojazdu: ");
                        String markaPojazdu = scanner.nextLine();
                        if(markaPojazdu.isEmpty()){
                            throw new PodanoNiepoprawneDaneLubPusty("ERROR: Marka pojazdu nie moze byc pusta. \n");
                        }

                        System.out.print("Model pojazdu: ");
                        String modelPojazdu = scanner.nextLine();
                        if(modelPojazdu.isEmpty()){
                            throw new PodanoNiepoprawneDaneLubPusty("ERROR: Model pojazdu nie moze byc pusty. \n");
                        }

                        switch (typPojazdu) {
                            case 1:
                                System.out.print("Czy posiada LPG? true/false: ");
                                boolean czyMaLPG = scanner.nextBoolean();

                                System.out.print("Ilu osobowy? ");
                                int iluOsobowy = scanner.nextInt();
                                if(iluOsobowy <=0){
                                    throw new PodanoNiepoprawneDaneLubPusty("ERROR: Wprowadzono nieprawidlowa ilosc miejsc. \n");
                                }

                                Pojazd pojazd = new Samochod(nrRejestracyjny, markaPojazdu, modelPojazdu, aktualnyCzas, czyMaLPG, iluOsobowy);
                                pojazdy.add(pojazd);
                                historia.add(new RejestrParkowania(pojazd));
                                break;
                            case 2:
                                System.out.print("Jaka wysokosc w cm? ");
                                double wysokosc = scanner.nextDouble();
                                if(wysokosc <=0){
                                    throw new PodanoNiepoprawneDaneLubPusty("ERROR: Wprowadzono nieprawidlowa wysokosc. \n");
                                }

                                System.out.print("Jaka ladownosc? ");
                                double ladownosc = scanner.nextDouble();
                                if(ladownosc<=0){
                                    throw new PodanoNiepoprawneDaneLubPusty("ERROR: Wprowadzono nieprawidlowa ladownosc. \n");
                                }

                                pojazd = new Ciezarowka(nrRejestracyjny, markaPojazdu, modelPojazdu, aktualnyCzas, wysokosc, ladownosc);
                                pojazdy.add(pojazd);
                                historia.add(new RejestrParkowania(pojazd));
                                break;
                            case 3:
                                System.out.print("Pojemnosc silnika: ");
                                int pojemnoscSilnika = scanner.nextInt();
                                if(pojemnoscSilnika <=0){
                                    throw new PodanoNiepoprawneDaneLubPusty("ERROR: Wprowadzono nieprawidlowa pojemnosc silnika. \n");
                                }
                                scanner.nextLine(); // usuwanie /n z buforu

                                System.out.print("Typ? cross/sportowy/scigacz: ");
                                String typ = scanner.nextLine();
                                if(!(typ.equals("cross") || typ.equals("sportowy") || typ.equals("scigacz"))){
                                    throw new PodanoNiepoprawneDaneLubPusty("ERROR: Wprowadzono nieprawidlowy typ motocyklu, cross/sportowy/scigacz. \n");
                                }

                                pojazd = new Motocykl(nrRejestracyjny, markaPojazdu, modelPojazdu, aktualnyCzas, pojemnoscSilnika, typ);
                                pojazdy.add(pojazd);
                                historia.add(new RejestrParkowania(pojazd));
                                break;
                        }
                    }
                    else{
                        throw new BrakMiejscaNaParkingu("ERROR: Brak miejsc na parkingu sprobuj ponownie pozniej. \n");
                    }
                }
                catch(BrakMiejscaNaParkingu error){
                    System.out.println(error.getMessage());
                }
                catch(TakaRejestracjaJestJuzNaParkingu error){
                    System.out.println(error.getMessage());
                }
                catch(PodanoNiepoprawneDaneLubPusty error){
                    System.out.println(error.getMessage());
                }
            }

            public double zarejestrujWyjazd(String nrRejestracyjny) {
                try {
                    for (Pojazd pojazd : pojazdy) {
                        if (pojazd.getNumerRejestracyjny().equals(nrRejestracyjny)) {
                            pojazd.wyjazdCzas(aktualnyCzas);
                            double oplata = pojazd.getOplata();
                            pojazdy.remove(pojazd);
                            return oplata;
                        }
                    }
                    throw new NieMaTakiegoSamochoduNaParkingu("ERROR: Pojazd o podanych numerach rejestracyjnych nie znajduje się na parkingu.\n");

                } catch (NieMaTakiegoSamochoduNaParkingu error) {
                    System.out.println(error.getMessage());
                }

                return 0.0;
            }


            public void przesunCzas(){
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.printf("Jaka jednostka czasu? \n 1. Rok | 2. Miesiac | 3. Dzien | 4. Godzina | 5. Minuta | 6. Sekunda : ");
                    int wyborJednostkiCzasu = scanner.nextInt();
                    if(!(wyborJednostkiCzasu >=1 && wyborJednostkiCzasu <= 6)){
                        throw new NiepoprawnaJednostkaCzasu("ERROR: Podano niepoprawna jednostke czasu, wybierz liczbe z zakresu 1-6. ");
                    };

                    System.out.printf("O ile jednostek? ");
                    int oIleJednostek = scanner.nextInt();
                    switch (wyborJednostkiCzasu) {
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
                    }
                }
                catch (NiepoprawnaJednostkaCzasu error) {
                    System.out.println(error.getMessage());
                }
            }

            public class BrakMiejscaNaParkingu extends Exception {
                public BrakMiejscaNaParkingu(String komunikat) {
                    super(komunikat);
                }
            }

            public class TakaRejestracjaJestJuzNaParkingu extends Exception {
                public TakaRejestracjaJestJuzNaParkingu(String komunikat) {
                    super(komunikat);
                }
            }

            public class PodanoNiepoprawneDaneLubPusty extends Exception {
                public PodanoNiepoprawneDaneLubPusty(String komunikat) {
                    super(komunikat);
                }
            }

            public class NieMaTakiegoSamochoduNaParkingu extends Exception {
                public NieMaTakiegoSamochoduNaParkingu(String komunikat) {
                    super(komunikat);
                }
            }

            public class NiepoprawnaJednostkaCzasu extends Exception {
                public NiepoprawnaJednostkaCzasu(String komunikat) {
                    super(komunikat);
                }
            }



            // Damian Sadowski - Koniec kodu

            // PAULINA CHOJNOWSKA POCZATEK KODU
            public boolean sprawdzDostepnosc() {
                return pojazdy.size() < pojemność;
            }

            public List<Pojazd> pobierzAktualnePojazdy() throws BladPobieraniaPojazdow {
                try {
                    return new ArrayList<>(pojazdy);
                } catch (Exception e) {
                    throw new BladPobieraniaPojazdow("Błąd podczas pobierania pojazdów: " + e.getMessage());
                }
            }




            public String generujRaportDzienny() throws BladGenerowaniaRaportu {
                try {
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

                } catch (Exception e) {
                    throw new BladGenerowaniaRaportu("Błąd podczas generowania raportu dziennego: " + e.getMessage());
                }
            }


            public void wyswietlHistorie() {
                try {
                    if (historia.isEmpty()) {
                        throw new PustaHistoriaException("Brak historii parkowania.");
                    }

                    System.out.println("Historia parkowania:");

                    for (RejestrParkowania rejestr : historia) {
                        Pojazd p = rejestr.getPojazd();
                        String typPojazdu;

                        if (p instanceof Samochod) {
                            typPojazdu = "Samochód osobowy";
                        } else if (p instanceof Motocykl) {
                            typPojazdu = "Motocykl";
                        } else if (p instanceof Ciezarowka) {
                            typPojazdu = "Ciężarówka";
                        } else {
                            typPojazdu = "Nieznany typ";
                        }

                        String statusWyjazdu;
                        LocalDateTime czasWyjazdu = p.getCzasWyjazdu();

                        if (czasWyjazdu == null) {
                            statusWyjazdu = "WYJECHAŁ: NIE (nadal zaparkowany)";
                        } else {
                            statusWyjazdu = "WYJECHAŁ: TAK, o " + czasWyjazdu;
                        }

                        System.out.println(
                                "Typ: " + typPojazdu +
                                        ", Rejestracja: " + p.getNumerRejestracyjny() +
                                        ", Marka: " + p.getMarka() +
                                        ", Model: " + p.getModel() +
                                        ", WJAZD: " + p.getCzasWjazdu() +
                                        ", " + statusWyjazdu +
                                        ", Opłata: " + rejestr.getOplata() + " zł"
                        );
                    }

                } catch (PustaHistoriaException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Wystąpił nieoczekiwany błąd podczas wyświetlania historii: " + e.getMessage());
                }
            }
            public class PustaHistoriaException extends Exception {
                public PustaHistoriaException(String komunikat) {
                    super(komunikat);
                }
            }
            public class BladGenerowaniaRaportu extends Exception {
                public BladGenerowaniaRaportu(String komunikat) {
                    super(komunikat);
                }
            }
            public class BladPobieraniaPojazdow extends Exception {
                public BladPobieraniaPojazdow(String komunikat) {
                    super(komunikat);
                }
            }

            // PAULINA CHOJNOWSKA KONIEC KODU

    public static void main(String[] args) {
        SystemParkingowy systemParkingowy = new SystemParkingowy(50, 10.0); // Dodane parametry
        Scanner scanner = new Scanner(System.in);
        int wybor;
        do {
            System.out.println("Co chcesz zrobic? \n " +
                    "1. Wjazd | " + "2. Wyjazd | " + "3. Sprawdz Dostepnosc | " + "4. Lista aktualnych pojazdow | " + "5. Generuj raport dzienny | " + "6. Przesun czas symulacji | " + "7. Historia pojazdow | " + "0. Wyjdz : ");
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
                    try {
                        System.out.println("Aktualne pojazdy:");
                        for (Pojazd p : systemParkingowy.pobierzAktualnePojazdy()) {
                            String typ;
                            if (p instanceof Samochod) {
                                typ = "Samochód osobowy";
                            }
                            else if (p instanceof Ciezarowka) {
                                typ = "Ciężarówka";
                            }
                            else if (p instanceof Motocykl) {
                                typ = "Motocykl";
                            }
                            else {
                                typ = "Nieznany typ";
                            }
                            System.out.println("- Typ: " + typ + ", Numer rejestracyjny: " + p.getNumerRejestracyjny() + ", Model: " + p.getModel() + ", Marka: " + p.getMarka() + ", Czas wjazdu: " + p.getCzasWjazdu());
                        }
                    } catch (SystemParkingowy.BladPobieraniaPojazdow e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:
                    try {
                        System.out.println(systemParkingowy.generujRaportDzienny());
                    } catch (SystemParkingowy.BladGenerowaniaRaportu e) {
                        System.out.println(e.getMessage());
                    }

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