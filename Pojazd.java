import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.Duration;

public abstract class Pojazd {
    private String numerRejestracyjny;
    private String marka;
    private String model;
    private LocalDateTime czasWjazdu;
    private LocalDateTime czasWyjazdu;
    private double oplata;

    public Pojazd(String numerRejestracyjny, String marka, String model, LocalDateTime czasWjazdu) {
        this.numerRejestracyjny = numerRejestracyjny;
        this.marka = marka;
        this.model = model;
        this.czasWjazdu = czasWjazdu;
    }
    public void wyjazdCzas(LocalDateTime czasWyjazdu)
    {
         this.czasWyjazdu=czasWyjazdu;
         this.oplata=obliczOplate();
    }

    public Duration pobierzCzasPostoju()
    {
        if(czasWyjazdu==null)
        {
            return Duration.ZERO;
        }
        return Duration.between(czasWjazdu, czasWyjazdu);
    }

    public double obliczOplate(){
        Duration postoj=pobierzCzasPostoju();
        double godziny=postoj.toHours();
        DayOfWeek dzien=czasWjazdu.getDayOfWeek();
        double ileOplata;
        if(dzien==DayOfWeek.SATURDAY || dzien==DayOfWeek.SUNDAY)
        {
             ileOplata=4.30;
        }
        else
        {
            ileOplata=2.30;
        }
       return ileOplata*godziny;

    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public LocalDateTime getCzasWjazdu() {
        return czasWjazdu;
    }

    public LocalDateTime getCzasWyjazdu() {
        return czasWyjazdu;
    }

    public double getOplata() {
        return oplata;
    }
}
