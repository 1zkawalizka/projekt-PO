import java.time.LocalDateTime;

public class Ciezarowka extends Pojazd {
    private double wysokosc;
    private double ladownosc;

    public Ciezarowka(String numerRejestracyjny, String marka, String model, LocalDateTime czasWjazdu, double wysokosc,
            double ladownosc) {
        super(numerRejestracyjny, marka, model, czasWjazdu);
        this.wysokosc = wysokosc;
        this.ladownosc = ladownosc;
    }

    public boolean czyDozwolonyWjazd() {
        if (wysokosc > 5 && ladownosc > 4) {
            return false;
        }
        return true;
    }

    @Override
    public double obliczOplate() {
        double ileZaGodzine = super.obliczOplate();
        if (wysokosc > 5 && ladownosc < 4)
        {   
            ileZaGodzine+=100;

        }
          return ileZaGodzine;
    }
}
