import java.time.LocalDateTime;

public class Motor extends Pojazd {
    private int pojemnoscSilnika;
    private String typ;

    public Motor(String numerRejestracyjny, String marka, String model, LocalDateTime czasWjazdu, int pojemnoscSilnika, String typ) {
        super(numerRejestracyjny, marka, model, czasWjazdu);
        this.pojemnoscSilnika = pojemnoscSilnika;
        this.typ = typ;
    }

    public boolean czyDozwolonyWjazd() {
        return pojemnoscSilnika <600;
    }
    @Override
    public double obliczOplate() {
        double ileZaGodzine = super.obliczOplate();
        if (typ.equalsIgnoreCase("cross") || typ.equalsIgnoreCase("sportowy"))
        {   
            if(ileZaGodzine-2 !=0)
            {
            ileZaGodzine-=2;
            }
           return ileZaGodzine;
        }
         else if (typ.equalsIgnoreCase("turystyczny"))
         {
            ileZaGodzine+=2;
         }
          return ileZaGodzine;
    }
}
