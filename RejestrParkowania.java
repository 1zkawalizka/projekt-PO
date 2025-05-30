import java.time.Duration;
import java.time.LocalDateTime;


public class RejestrParkowania {
    private LocalDateTime czasWjazdu; //
    private LocalDateTime czasWyjazdu;
    private Pojazd pojazd;
    private double oplata;

    public RejestrParkowania(Pojazd pojazd) {
        this.czasWjazdu = pojazd.getCzasWjazdu();
        this.czasWyjazdu = pojazd.getCzasWyjazdu();
        this.pojazd = pojazd;
        this.oplata = pojazd.getOplata();
    }

    public Duration pobierzCzasPostoju(){
        Duration czasPostoju = Duration.between(czasWjazdu, czasWyjazdu);
        return czasPostoju;
    }

    public static void main(String[] args) {
    }
}