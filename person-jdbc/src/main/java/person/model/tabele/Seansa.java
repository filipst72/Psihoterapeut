package person.model.tabele;

import java.time.LocalDate;
import java.time.LocalTime;

public class Seansa {
    private final int seansa_id;
    private LocalDate dan;
    private LocalTime vreme;
    private int trajanje;
    private String beleske;
    private int cena_seanse_id;
    private int kandidat_id;
    private int psihoterapeut_id;
    private int klijent_id;
    private int uplata_id;

    public Seansa(int seansa_id, LocalDate dan, LocalTime vreme, int trajanje, String beleske, int cena_seanse_id, int kandidat_id, int psihoterapeut_id, int klijent_id, int uplata_id) {
        this.seansa_id = seansa_id;
        this.dan = dan;
        this.vreme = vreme;
        this.trajanje = trajanje;
        this.beleske = beleske;
        this.cena_seanse_id = cena_seanse_id;
        this.kandidat_id = kandidat_id;
        this.psihoterapeut_id = psihoterapeut_id;
        this.klijent_id = klijent_id;
        this.uplata_id = uplata_id;
    }

    public int getSeansa_id() {
        return seansa_id;
    }

    public LocalDate getDan() {
        return dan;
    }

    public void setDan(LocalDate dan) {
        this.dan = dan;
    }

    public LocalTime getVreme() {
        return vreme;
    }

    public void setVreme(LocalTime vreme) {
        this.vreme = vreme;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public String getBeleske() {
        return beleske;
    }

    public void setBeleske(String beleske) {
        this.beleske = beleske;
    }

    public int getCena_seanse_id() {
        return cena_seanse_id;
    }

    public void setCena_seanse_id(int cena_seanse_id) {
        this.cena_seanse_id = cena_seanse_id;
    }

    public int getKandidat_id() {
        return kandidat_id;
    }

    public void setKandidat_id(int kandidat_id) {
        this.kandidat_id = kandidat_id;
    }

    public int getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(int psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public int getKlijent_id() {
        return klijent_id;
    }

    public void setKlijent_id(int klijent_id) {
        this.klijent_id = klijent_id;
    }

    public int getUplata_id() {
        return uplata_id;
    }

    public void setUplata_id(int uplata_id) {
        this.uplata_id = uplata_id;
    }
}
