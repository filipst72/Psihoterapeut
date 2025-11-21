package person.model.tabele;
import javafx.beans.property.*;

public class PrijavaPrikaz {
    private Prijava_za_seansu prijava;
    private Klijent klijent;
    private Nacin_komunikacije komunikacija;

    public PrijavaPrikaz(Prijava_za_seansu prijava, Klijent klijent, Nacin_komunikacije komunikacija) {
        this.prijava = prijava;
        this.klijent = klijent;
        this.komunikacija = komunikacija;
    }

    public Prijava_za_seansu getPrijava() {
        return prijava;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public Nacin_komunikacije getKomunikacija() {
        return komunikacija;
    }

    public int getPrijavaId() {
        return prijava.getPrijava_id();
    }

    public StringProperty klijentImePrezimeProperty() {
        return new SimpleStringProperty(klijent.getIme() + " " + klijent.getPrezime());
    }

    public StringProperty komunikacijaKanalProperty() {
        return new SimpleStringProperty(komunikacija.getKanal());
    }
}
