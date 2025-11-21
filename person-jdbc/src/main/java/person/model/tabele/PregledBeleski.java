package person.model.tabele;

import java.time.LocalDate;
import java.time.LocalTime;

public class PregledBeleski {
    private final int seansaId;
    private final LocalDate datum;
    private final LocalTime vreme;
    private final int trajanje;
    private final String klijent;
    private final String klijentKontakt;
    private final String beleske;
    private final String testovi;
    private final double cenaSeanse;
    private final String statusPublikacije;
    private final String nacinPrijave;
    private final String tipSeanse;

    public PregledBeleski(int seansaId, LocalDate datum, LocalTime vreme, int trajanje, String klijent, String klijentKontakt, String beleske, String testovi, double cenaSeanse, String statusPublikacije, String nacinPrijave, String tipSeanse) {
        this.seansaId = seansaId;
        this.datum = datum;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.klijent = klijent;
        this.klijentKontakt = klijentKontakt;
        this.beleske = beleske;
        this.testovi = testovi;
        this.cenaSeanse = cenaSeanse;
        this.statusPublikacije = statusPublikacije;
        this.nacinPrijave = nacinPrijave;
        this.tipSeanse = tipSeanse;
    }

    public int getSeansaId() {
        return seansaId;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getVreme() {
        return vreme;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public String getKlijent() {
        return klijent;
    }

    public String getKlijentKontakt() {
        return klijentKontakt;
    }

    public String getBeleske() {
        return beleske;
    }

    public String getTestovi() {
        return testovi;
    }

    public double getCenaSeanse() {
        return cenaSeanse;
    }

    public String getStatusPublikacije() {
        return statusPublikacije;
    }

    public String getNacinPrijave() {
        return nacinPrijave;
    }

    public String getTipSeanse() {
        return tipSeanse;
    }
}
