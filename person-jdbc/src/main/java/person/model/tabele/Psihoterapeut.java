package person.model.tabele;

import java.sql.Date;
import java.time.LocalDate;

public class Psihoterapeut {
    private final int psihoterapeut_id;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datum_rodjenja;
    private String prebivaliste;
    private String br_telefona;
    private String email;
    private LocalDate datum_sertifikacije;
    private boolean psiholog;
    private int oblast_id;
    private int centar_id;

    public Psihoterapeut(int psihoterapeut_id, String ime, String prezime, String jmbg, LocalDate datum_rodjenja, String prebivaliste, String br_telefona, String email, LocalDate datum_sertifikacije, boolean psiholog, int oblast_id, int centar_id) {
        this.psihoterapeut_id = psihoterapeut_id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datum_rodjenja = datum_rodjenja;
        this.prebivaliste = prebivaliste;
        this.br_telefona = br_telefona;
        this.datum_sertifikacije = datum_sertifikacije;
        this.email = email;
        this.psiholog = psiholog;
        this.oblast_id = oblast_id;
        this.centar_id = centar_id;
    }

    public int getPsihoterapeut_id() {
        return psihoterapeut_id;
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

    public LocalDate getDatum_sertifikacije() {
        return datum_sertifikacije;
    }

    public void setDatum_sertifikacije(LocalDate datum_sertifikacije) {
        this.datum_sertifikacije = datum_sertifikacije;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPsiholog() {
        return psiholog;
    }

    public void setPsiholog(boolean psiholog) {
        this.psiholog = psiholog;
    }

    public int getOblast_id() {
        return oblast_id;
    }

    public void setOblast_id(int oblast_id) {
        this.oblast_id = oblast_id;
    }

    public int getCentar_id() {
        return centar_id;
    }

    public void setCentar_id(int centar_id) {
        this.centar_id = centar_id;
    }
}
