package person.model.tabele;

import javafx.scene.text.Text;

import java.time.LocalDate;

public class Klijent {
    private final int klijent_id;
    private String ime;
    private String prezime;
    private LocalDate datum_rodjenja;
    private String pol;
    private String email;
    private String br_telefona;
    private boolean prethodno_iskustvo;
    private String opis_pronlema;

    public Klijent(int klijent_id, String ime, String prezime, LocalDate datum_rodjenja, String pol, String email, String br_telefona, boolean prethodno_iskustvo, String opis_pronlema) {
        this.klijent_id = klijent_id;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_rodjenja = datum_rodjenja;
        this.pol = pol;
        this.email = email;
        this.br_telefona = br_telefona;
        this.prethodno_iskustvo = prethodno_iskustvo;
        this.opis_pronlema = opis_pronlema;
    }

    public int getKlijent_id() {
        return klijent_id;
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

    public LocalDate getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(LocalDate datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBr_telefona() {
        return br_telefona;
    }

    public void setBr_telefona(String br_telefona) {
        this.br_telefona = br_telefona;
    }

    public boolean isPrethodno_iskustvo() {
        return prethodno_iskustvo;
    }

    public void setPrethodno_iskustvo(boolean prethodno_iskustvo) {
        this.prethodno_iskustvo = prethodno_iskustvo;
    }

    public String getOpis_pronlema() {
        return opis_pronlema;
    }

    public void setOpis_pronlema(String opis_pronlema) {
        this.opis_pronlema = opis_pronlema;
    }
}
