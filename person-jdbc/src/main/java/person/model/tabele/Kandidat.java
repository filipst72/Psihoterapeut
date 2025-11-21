package person.model.tabele;

import java.time.LocalDate;

public class Kandidat {
    private final int kandidat_id;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datum_rodjenja;
    private String prebivaliste;
    private String br_telefona;
    private String email;
    private String stepen_studija;
    private LocalDate datum_pocetka;
    private LocalDate datum_kraja;
    private boolean psiholog;
    private int centar_id;
    private int psihoterapeut_id;
    private int fakultet_id;

    public Kandidat(int kandidat_id, String ime, String prezime, String jmbg, LocalDate datum_rodjenja, String prebivaliste, String br_telefona, String email, String stepen_studija, LocalDate datum_pocetka, LocalDate datum_kraja, boolean psiholog, int centar_id, int psihoterapeut_id, int fakultet_id) {
        this.kandidat_id = kandidat_id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datum_rodjenja = datum_rodjenja;
        this.prebivaliste = prebivaliste;
        this.br_telefona = br_telefona;
        this.email = email;
        this.stepen_studija = stepen_studija;
        this.datum_pocetka = datum_pocetka;
        this.datum_kraja = datum_kraja;
        this.psiholog = psiholog;
        this.centar_id = centar_id;
        this.psihoterapeut_id = psihoterapeut_id;
        this.fakultet_id = fakultet_id;
    }

    public int getKandidat_id() {
        return kandidat_id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(LocalDate datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public String getPrebivaliste() {
        return prebivaliste;
    }

    public void setPrebivaliste(String prebivaliste) {
        this.prebivaliste = prebivaliste;
    }

    public String getBr_telefona() {
        return br_telefona;
    }

    public void setBr_telefona(String br_telefona) {
        this.br_telefona = br_telefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStepen_studija() {
        return stepen_studija;
    }

    public void setStepen_studija(String stepen_studija) {
        this.stepen_studija = stepen_studija;
    }

    public LocalDate getDatum_pocetka() {
        return datum_pocetka;
    }

    public void setDatum_pocetka(LocalDate datum_pocetka) {
        this.datum_pocetka = datum_pocetka;
    }

    public LocalDate getDatum_kraja() {
        return datum_kraja;
    }

    public void setDatum_kraja(LocalDate datum_kraja) {
        this.datum_kraja = datum_kraja;
    }

    public boolean isPsiholog() {
        return psiholog;
    }

    public void setPsiholog(boolean psiholog) {
        this.psiholog = psiholog;
    }

    public int getCentar_id() {
        return centar_id;
    }

    public void setCentar_id(int centar_id) {
        this.centar_id = centar_id;
    }

    public int getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(int psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public int getFakultet_id() {
        return fakultet_id;
    }

    public void setFakultet_id(int fakultet_id) {
        this.fakultet_id = fakultet_id;
    }
}
