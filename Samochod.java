import java.time.LocalDateTime;

public class Samochod extends Pojazd
{
    private boolean czyMaLPG;
    private int iloscMiejsc;

    public Samochod(String numerRejestracyjny, String marka, String model, LocalDateTime czasWjazdu,boolean czyMaLPG, int iloscMiejsc )
    {
        super(numerRejestracyjny,marka,model,czasWjazdu);
        this.czyMaLPG=czyMaLPG;
        this.iloscMiejsc=iloscMiejsc;
    }
   public boolean czyDozwolonyWjazd()
   {
    return !czyMaLPG;
   }

    @Override
   public double obliczOplate()
   {
    double ileZaGodzine=super.obliczOplate();
    if(iloscMiejsc>8)
    {
       ileZaGodzine+=5;
    }
      return ileZaGodzine;
   }
}
