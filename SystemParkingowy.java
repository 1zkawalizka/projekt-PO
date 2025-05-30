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
        this.aktualnyCzas = LocalDateTime.of(2025, 6, 1, 15, 25, 0); // LocalDateTime.of(rok, miesiac, dzien, godzina, minuta, sekunda)
        // Damian Sadowski - Koniec kodu
    }

    // Damian Sadowski - Poczatek kodu
    public void zarejestrujWjazd(Pojazd pojazd){
        pojazd = new Samochod(pojazd.getNumerRejestracyjny(), pojazd.getMarka(), pojazd.getModel(), LocalDateTime.of(2025, 6, 1, 15, 25, 0), true, 5);
        pojazdy.add(pojazd);
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
                System.out.println(aktualnyCzas + "-> + " + oIleJednostek + " Lat -> " + aktualnyCzas.plusYears(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusYears(oIleJednostek);
                break;
            case 2:
                System.out.println(aktualnyCzas + "-> + " + oIleJednostek + " Miesiecy -> " + aktualnyCzas.plusMonths(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusMonths(oIleJednostek);
                break;
            case 3:
                System.out.println(aktualnyCzas + "-> + " + oIleJednostek + " Dni-> " + aktualnyCzas.plusDays(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusDays(oIleJednostek);
                break;
            case 4:
                System.out.println(aktualnyCzas + "-> + " + oIleJednostek + " Godzin -> " + aktualnyCzas.plusHours(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusHours(oIleJednostek);
                break;
            case 5:
                System.out.println(aktualnyCzas + "-> + " + oIleJednostek + " Minut -> " + aktualnyCzas.plusMinutes(oIleJednostek));
                aktualnyCzas = aktualnyCzas.plusMinutes(oIleJednostek);
                break;
            case 6:
                System.out.println(aktualnyCzas + "-> + " + oIleJednostek + " Sekund -> " + aktualnyCzas.plusSeconds(oIleJednostek));
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
        systemParkingowy.przesunCzas();
        systemParkingowy.przesunCzas();
        // Damian Sadowski - Koniec Kodu
    }
}